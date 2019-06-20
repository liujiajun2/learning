package xin.liujiajun.socket.internet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author LiuJiaJun
 * @date 2019/6/14 21:01
 */
public class HttpURLConnectionDemo {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.baidu.com");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        //output:200
        System.out.println(connection.getResponseCode());
        //OK
        System.out.println(connection.getResponseMessage());
    }


}
