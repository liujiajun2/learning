package xin.liujiajun.socket.udp;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * @author LiuJiaJun
 * @date 2019/5/10 16:29
 */
public class UDPPoke {
    private int bufferSize;
    private int timeout;
    private InetAddress host;
    private int port;

    public UDPPoke(InetAddress host, int port, int bufferSize, int timeout) {
        this.bufferSize = bufferSize;
        this.timeout = timeout;
        this.host = host;
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("port out of range");
        }
        this.port = port;
    }

    public UDPPoke(int bufferSize, InetAddress host, int port) {
        this(host, port, bufferSize, 30000);
    }

    public UDPPoke(InetAddress host, int port) {
        this(host, port, 8192, 30000);
    }

    public byte[] poke() {

        try (DatagramSocket socket = new DatagramSocket(0)) {
            DatagramPacket out = new DatagramPacket(new byte[1], 1, host, port);
            socket.connect(host, port);
            socket.setSoTimeout(timeout);
            socket.send(out);

            DatagramPacket income = new DatagramPacket(new byte[bufferSize], bufferSize);
            //阻塞，直到收到响应
            socket.receive(income);

            int numBytes = income.getLength();
            byte[] response = new byte[numBytes];
            System.arraycopy(income.getData(), 0, response, 0, numBytes);
            return response;
        } catch (IOException e) {
            System.out.println("抛出异常");
            return null;
        }
    }


    public static void main(String[] args) throws UnknownHostException, UnsupportedEncodingException {
        UDPPoke udpPoke = new UDPPoke(InetAddress.getByName("localhost"), 13);
        byte[] response = udpPoke.poke();
        if (response == null) {
            System.out.println("");
            return;
        }

        System.out.println(new String(response, StandardCharsets.UTF_8));
    }
}
