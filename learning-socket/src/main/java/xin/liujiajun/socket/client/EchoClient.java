package xin.liujiajun.socket.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author LiuJiaJun
 * @date 2019/5/10 10:19
 */
public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",7);
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter write = new OutputStreamWriter(outputStream);
        write.write("dahsfhadjfhdhjfaf");

        while (true){
            InputStream inputStream = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            char [] buf = new char[1024];
            int len = 0;
            while ((len = reader.read(buf)) != 0){
                System.out.println(new String(buf));
            }
        }

    }
}
