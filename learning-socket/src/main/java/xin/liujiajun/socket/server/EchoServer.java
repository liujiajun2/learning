package xin.liujiajun.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LiuJiaJun
 * @date 2019/5/10 10:04
 */
public class EchoServer {
    private static int DEFAULT_PORT = 7;

    public static void main(String[] args) {
        ServerSocketChannel socketChannel;
        Selector selector;

        try {
            socketChannel = ServerSocketChannel.open();
            ServerSocket socket = socketChannel.socket();
            socket.bind(new InetSocketAddress(DEFAULT_PORT));
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true){
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()){
                        final ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        final SocketChannel client = server.accept();
                        System.out.println("accepted connection from " + client);
                        client.configureBlocking(false);
                        final SelectionKey clientKey = client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);

                        final ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientKey.attach(buffer);
                    }
                    if (key.isReadable()){
                        System.out.println("read");
                        final SocketChannel client = (SocketChannel)key.channel();
                        final ByteBuffer output = (ByteBuffer)key.attachment();
                        client.read(output);

                    }
                    if (key.isWritable()){
                        System.out.println("write");
                        final SocketChannel client = (SocketChannel)key.channel();
                        final ByteBuffer output = (ByteBuffer)key.attachment();

                        output.flip();
                        client.write(output);
                        output.compact();
                    }
                } catch (IOException e) {
                   key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
