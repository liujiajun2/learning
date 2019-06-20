package xin.liujiajun.socket.internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author LiuJiaJun
 * @date 2019/6/12 21:59
 */
public class InetAddressDemo {

    public static void main(String[] args) throws UnknownHostException {
//      test01();
//      test02();
//      test03();
//      test04();
        test05();
    }

    public static void test01() throws UnknownHostException {
        //InetAddress是Java对IP地址（IPV4和IPV6）的高层表示
        InetAddress adderss = InetAddress.getByName("www.baidu.com");
        //输出结果：www.baidu.com/14.215.177.38
        System.out.println(adderss);
    }

    public static void test02() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("14.215.177.39");
        //如果没相应的主机名，返回的还是IP地址
        //output：/14.215.177.39
        System.out.println(address);
        //output：14.215.177.39
        System.out.println(address.getHostName());
    }
    public static void test03() throws UnknownHostException {
        InetAddress[] inetAddresses = InetAddress.getAllByName("www.baidu.com");
        for (InetAddress inetAddress : inetAddresses){
            System.out.println(inetAddress);
        }
        //output:
        //www.baidu.com/14.215.177.38
        //www.baidu.com/14.215.177.39
    }
    public static void test04() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        //output：TR/192.168.116.1
        System.out.println(localHost);
    }
    public static void test05() throws UnknownHostException {
        byte[] ip = {14,(byte)215, (byte) 177,38};
        InetAddress address = InetAddress.getByAddress(ip);
        InetAddress inetAddress = InetAddress.getByAddress("www.baidu.com", ip);

        //output:/14.215.177.38
        System.out.println(address);
        //output:www.baidu.com/14.215.177.38
        System.out.println(inetAddress);

    }
}
