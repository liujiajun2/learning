package xin.liujiajun.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author LiuJiaJun
 * @date 2019/6/20 9:48
 */
public class EchoServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(13);
        while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
            server.receive(request);
            System.out.println(new String(request.getData()));
            DatagramPacket response = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
            server.send(response);
        }
    }
}
