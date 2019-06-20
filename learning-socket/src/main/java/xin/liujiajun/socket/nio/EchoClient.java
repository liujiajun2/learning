package xin.liujiajun.socket.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author LiuJiaJun
 * @date 2019/6/18 21:37
 */
public class EchoClient {
    public static void main(String[] args) throws IOException {
        //建立连接
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 9090));

        byte[] bytes = "hello world".getBytes();
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        //写入数据
        channel.write(wrap);

        ByteBuffer read = ByteBuffer.allocate(1024);
        //读取数据
        channel.read(read);
        System.out.println(new String(read.array()));
        //关闭连接
        channel.close();
    }

}
