package xin.liujiajun.socket.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author LiuJiaJun
 * @date 2019/6/14 16:22
 */
public class URLConnectionDemo {
    public static void main(String[] args) throws IOException {
//        test1();
//        read();
        header();
    }

    public static void test1() throws IOException {
        URL url = new URL("http://www.baidu.com");
        URLConnection connection = url.openConnection();
        String s = connection.getContentType();
        System.out.println(s);
    }

    public static void read() {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection connection = url.openConnection();
            try (InputStream inputStream = connection.getInputStream();) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String info = "";
                String str = "";
                while ((str = reader.readLine()) != null) {
                    info += str;
                }
                System.out.println(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void header() {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection connection = url.openConnection();
            System.out.println(connection.getContentType());
            System.out.println(connection.getContentLength());
            System.out.println(connection.getContentEncoding());
            System.out.println(connection.getDate());
            System.out.println(connection.getLastModified());
            System.out.println(connection.getExpiration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
