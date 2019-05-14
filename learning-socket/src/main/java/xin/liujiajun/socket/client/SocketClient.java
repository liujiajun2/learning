package xin.liujiajun.socket.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author LiuJiaJun
 * @date 2019/4/12 22:22
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",13);

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        char [] buff = new char[1024];
        int len = 0;
        while ((len = inputStreamReader.read(buff)) != -1){
            System.out.println(String.valueOf(buff));
        }
    }
}
