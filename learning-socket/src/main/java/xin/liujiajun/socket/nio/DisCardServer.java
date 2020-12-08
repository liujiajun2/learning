package xin.liujiajun.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author liujiajun
 * @date 2020-11-28 14:32
 **/
public class DisCardServer {

    public static void startServer() throws IOException {
        //获取选择器
        Selector selector = Selector.open();
        //获取SocketServerChannel通道
        ServerSocketChannel server = ServerSocketChannel.open();
        //设置为非阻塞
        server.configureBlocking(false);
        //绑定端口
        server.bind(new InetSocketAddress(10101));
        //将通道注册到选择器，并监听ACCEPT事件
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //轮询感兴趣的IO就绪事件，阻塞方法
            selector.select();
            //获取选择键组合
            Iterator<SelectionKey> selectKeys = selector.selectedKeys().iterator();
            while (selectKeys.hasNext()) {
                //取单个选择键
                SelectionKey selectionKey = selectKeys.next();
                //如果IO事件，是连接就绪事件
                if (selectionKey.isAcceptable()) {
                    //获取客户端连接
                    SocketChannel client = server.accept();
                    //设置为非阻塞模式
                    client.configureBlocking(false);
                    //将通道注册到选择器，事件为：可读事件
                    client.register(selector, SelectionKey.OP_READ);

                    //如果IO事件是可读事件
                } else if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    //读取事件，然后丢弃
                    while ((len = channel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                    channel.close();
                }
                //移除选择键，否则会重复
                selectKeys.remove();
            }
        }
    }
}
