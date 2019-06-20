package xin.liujiajun.socket.udp;


import java.io.IOException;
import java.net.*;

/**
 * @author LiuJiaJun
 * @date 2019/6/20 9:48
 */
public class EchoClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(0);
        socket.setSoTimeout(10000);
        InetAddress address = InetAddress.getLocalHost();
        byte[] bytes = "hello".getBytes();
        DatagramPacket request = new DatagramPacket(bytes, bytes.length, address, 13);
        DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
        socket.send(request);
        socket.receive(response);

        String s = new String(response.getData(), 0, response.getLength());
        System.out.println(s);

    }
}
