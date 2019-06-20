package xin.liujiajun.socket.internet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author LiuJiaJun
 * @date 2019/6/14 15:27
 */
public class NetworkInterfaceDemo {

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
        test3();
    }

    public static void test1() throws SocketException {
        NetworkInterface eth0 = NetworkInterface.getByName("eth0");
        //output:name:eth0 (Qualcomm Wireless HS-USB Ethernet Adapter 9025)
        if (eth0 == null) {
            System.out.println("null");
        } else {
            System.out.println(eth0);
        }

    }

    public static void test2() throws SocketException, UnknownHostException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        NetworkInterface eth0 = NetworkInterface.getByInetAddress(address);
        //output:name:lo (Software Loopback Interface 1)
        if (eth0 == null) {
            System.out.println("null");
        } else {
            System.out.println(eth0);
        }

    }

    public static void test3() throws SocketException {
        Enumeration<NetworkInterface> f = NetworkInterface.getNetworkInterfaces();
        while (f.hasMoreElements()){
            NetworkInterface networkInterface = f.nextElement();
            System.out.println(networkInterface);
        }
    }
}
