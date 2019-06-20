package xin.liujiajun.socket.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author LiuJiaJun
 * @date 2019/6/20 12:35
 */
public class DatagramChannelServer {
    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(9090));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                //接受数据
                SocketAddress client = channel.receive(buffer);
                buffer.flip();
                //发送数据
                channel.send(buffer, client);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
