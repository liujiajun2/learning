package xin.liujiajun.socket.nio;


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
 * @author LiuJiaJun
 * @date 2019/6/18 20:52
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        //创建通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        //绑定连接
        channel.bind(new InetSocketAddress(9090));
        //设置非阻塞模式
        channel.configureBlocking(false);
        //创建Selector实例
        Selector selector = Selector.open();
        //通道注册到selector中。关心的事件是接收
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //查看就绪通道
            int selectNum = selector.select();
            System.out.println("select number is :" + selectNum);
            //返回通道和选择器 连接的键
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("key size：" + selectionKeys.size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //必须remove,否则下次select(),会将上次的SelectionKey也遍历一遍
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    //服务端Socket通道只能是监听
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    //监听连接
                    SocketChannel client = server.accept();

                    System.out.println("accepted from " + client);
                    //设置为非阻塞模式
                    client.configureBlocking(false);
                    //客户端连接注册到selector中。并且关心的事件是读数据
                    client.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    System.out.println("读取数据");
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int n = client.read(buffer);
                    //这里很重要，必须对没有数据传输的通道进行关闭。否则每次处理都会跑一遍代码
                    if (n == -1) {
                        System.out.println("关闭客户端");
                        client.close();
                        continue;
                    }
                    buffer.flip();
                    String s = new String(buffer.array());
                    System.out.println(s);
                    //将数据写会给客户端
                    client.write(buffer);
                }
            }
        }
    }
}
