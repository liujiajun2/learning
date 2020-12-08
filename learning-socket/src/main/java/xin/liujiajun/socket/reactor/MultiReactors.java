package xin.liujiajun.socket.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2020-11-17 16:55
 **/
public class MultiReactors {

    ServerSocketChannel serverSocket;
    Selector mainSelect;
    Selector[] selectors = new Selector[2];
    Reactor mainReactor = null;
    Reactor[] subReactors = null;
    AtomicInteger step = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        MultiReactors multiEchoHandler = new MultiReactors();
        multiEchoHandler.startService();
    }
    private void startService() throws InterruptedException {
        new Thread(mainReactor).start();
        new Thread(subReactors[0]).start();
        Thread thread = new Thread(subReactors[1]);
        thread.start();
        thread.join();
    }

    public MultiReactors() {
        try {
            mainSelect = Selector.open();

            selectors[0] = Selector.open();
            selectors[1] = Selector.open();
            serverSocket = ServerSocketChannel.open();
            serverSocket.socket().bind(new InetSocketAddress(9090));
            serverSocket.configureBlocking(false);

            //第一个选择器，负责监控新连接事件
            SelectionKey selectionKey = serverSocket.register(mainSelect, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new MultiReactors.Acceptor());

            mainReactor = new Reactor(mainSelect);

            Reactor subReactor1 = new Reactor(selectors[0]);
            Reactor subReactor2 = new Reactor(selectors[1]);
            subReactors = new Reactor[]{subReactor1,subReactor2};


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Reactor implements Runnable {
        final Selector selector;

        public Reactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();
                    while (it.hasNext()) {
                        Runnable handler = (Runnable) it.next().attachment();
                        if (handler != null) {
                            handler.run();
                        }
                    }
                    keys.clear();
                }
            } catch (IOException e) {

            }
        }

    }




    class Acceptor implements Runnable {

        @Override
        public void run() {
            SocketChannel client = null;
            try {
                client = serverSocket.accept();
                if (client != null) {
                    Selector selector = selectors[step.getAndIncrement() % selectors.length];
                    new MultiReactors.MultiEchoHandler(selector, client);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class MultiEchoHandler implements Runnable {
        SocketChannel socket;
        SelectionKey sk;
        ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
        final static int READING = 0, SENDING = 1;
        int state = READING;
        //避免重复创建
        static ExecutorService pool = Executors.newFixedThreadPool(4);

        public MultiEchoHandler(Selector selector, SocketChannel c) {

            try {
                /**
                 * 如果没有此代码，发现会无法注册，
                 * 这是因为从Reactor 目前正阻塞在select()方法上，
                 * 此方法锁定了publicKeys（已注册的key），直接注册会造成死锁，
                 * 通过调用wakeup，有可能还没注册成功又阻塞了。这是一个多线程同步的问题
                 */
                selector.wakeup();

                socket = c;
                c.configureBlocking(false);
                sk = socket.register(selector, SelectionKey.OP_READ);
                sk.attach(this);
//                sk.interestOps(SelectionKey.OP_READ);
                selector.wakeup();
            } catch (IOException ignore) {
                ignore.printStackTrace();
            }
        }

        @Override
        public void run() {
            //提交到线程池中执行
            pool.execute(this::doHandle);

        }

        private synchronized void doHandle(){
            try {
                if (state == READING) {
                    int length = 0;
                    while ((length = socket.read(buffer)) > 0) {
                        System.out.println(new String(buffer.array(), 0, length));
                    }
                    buffer.flip();
                    sk.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                } else if (state == SENDING) {

                    socket.write(buffer);

                    buffer.clear();
                    sk.interestOps(SelectionKey.OP_READ);
                    state = READING;
                }
            } catch (IOException e) {

            }
        }
    }

}
