package xin.liujiajun.thread.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuJiaJun
 * @date 2019/1/18 23:34
 */
public class NIOSocketClient {
    private Selector selector;
    private ExecutorService tp = new ThreadPoolExecutor(0, 10, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static Map<Socket, Long> timeStat = new HashMap<>(10240);

    private void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        InetSocketAddress ias = new InetSocketAddress("localhost", 8000);
        ssc.socket().bind(ias);

        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            long e = 0;
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    doAccept(key);
                } else if (key.isValid() && key.isReadable()) {
                    Socket socket = ((SocketChannel) key.channel()).socket();
                    if (timeStat.containsKey(socket)) {
                        timeStat.put(socket, System.currentTimeMillis());
                    }
                    doRead(key);
                } else if (key.isValid() && key.isWritable()) {
                    Socket socket = ((SocketChannel) key.channel()).socket();
                    doWrite(key);
                    e = System.currentTimeMillis();
                    long b = timeStat.remove(socket);
                    System.out.println("spend " + (e - b));
                }
            }
        }
    }

    private void doAccept(SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel;
        try {
            clientChannel = serverSocketChannel.accept();
            clientChannel.configureBlocking(false);

            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            InetAddress inetAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connection from " + inetAddress.getHostAddress() + ",");
        } catch (IOException e) {
            System.out.println("Failed to accept new client.");
            e.printStackTrace();
        }
    }

    private void doRead(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        int len;

        try {
           len = channel.read(buffer);
           if (len <0) {
               //
               return ;
           }
        } catch (IOException e) {
            System.out.println("failed to read to client");
            e.printStackTrace();
        }
        buffer.flip();
        tp.execute(new HandleMsg(key,buffer));
    }

    private void doWrite(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        EchoClient attachment = (EchoClient)key.attachment();
        LinkedList<ByteBuffer> outQueue = attachment.getOutQueue();

        ByteBuffer bb = outQueue.getLast();
        try {
            int len = channel.write(bb);
            if (len == -1) {
                //
                return ;
            }
            if(bb.remaining() == 0){
                outQueue.removeLast();
            }
        } catch (IOException e) {
            System.out.println("failed to write to client");
            e.printStackTrace();
        }
        if(outQueue.size() == 0) {
            key.interestOps(SelectionKey.OP_READ);
        }
    }


    class EchoClient {
        private LinkedList<ByteBuffer> outQueue;

        EchoClient() {
            outQueue = new LinkedList<>();
        }

        public LinkedList<ByteBuffer> getOutQueue() {
            return outQueue;
        }

        public void enqueue(ByteBuffer byteBuffer) {
            outQueue.addFirst(byteBuffer);
        }
    }

    class HandleMsg implements Runnable{
        SelectionKey key;
        ByteBuffer byteBuffer;

        public HandleMsg(SelectionKey key, ByteBuffer byteBuffer) {
            this.key = key;
            this.byteBuffer = byteBuffer;
        }

        @Override
        public void run() {
            EchoClient attachment = (EchoClient) key.attachment();
            attachment.enqueue(byteBuffer);
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            selector.wakeup();
        }
    }


    public static void main(String[] args) {
        NIOSocketClient nioSocketClient = new NIOSocketClient();
        try {
            nioSocketClient.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
