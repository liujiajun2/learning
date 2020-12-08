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

/**
 * @author liujiajun
 * @date 2020-11-17 08:31
 **/
public class Reactor implements Runnable {

    Selector selector;
    ServerSocketChannel serverSocket;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Reactor());
        thread.start();
        thread.join();
    }

    public Reactor() {
        try {
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();
            serverSocket.socket().bind(new InetSocketAddress(9090));
            serverSocket.configureBlocking(false);

            SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new Acceptor());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    dispatch(it.next());
                }
                selected.clear();
            }
        } catch (IOException e) {

        }
    }

    void dispatch(SelectionKey key) {
        Runnable handler = (Runnable) key.attachment();
        if (handler != null) {
            handler.run();
        }
    }

    class Acceptor implements Runnable {

        @Override
        public void run() {
            SocketChannel client = null;
            try {
                client = serverSocket.accept();
                if (client != null) {
                    new Handler(selector, client);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Handler implements Runnable {
        SocketChannel socket;
        SelectionKey sk;
        ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
        final static int READING = 0, SENDING = 1;
        int state = READING;

        public Handler(Selector selector, SocketChannel c) {
            try {
                socket = c;
                c.configureBlocking(false);
                sk = socket.register(selector, 0);
                sk.attach(this);
                sk.interestOps(SelectionKey.OP_READ);
                selector.wakeup();
            } catch (IOException ignore) {
                ignore.printStackTrace();
            }
        }

        @Override
        public void run() {
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
