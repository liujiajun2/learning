package xin.liujiajun.socket.bio;

import java.io.*;
import java.net.Socket;

/**
 * @author LiuJiaJun
 * @date 2019/6/12 14:28
 */
public class EchoClient {

    public static void main(String[] args) throws IOException {
        //创建socket并连接到本地 9090端口
        Socket socket = new Socket("localhost", 9090);
        //获取输出流
        OutputStreamWriter write = new OutputStreamWriter(socket.getOutputStream());
        //获取输入流
        InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        //向服务器写数据
        write.write("hello world!!!" + "\r\n");
        write.flush();
        //关闭输出流
        socket.shutdownOutput();

        char[] buff = new char[1024];
        int len = 0;
        while ((len = reader.read(buff)) != -1) {
            System.out.println(new String(buff));
        }
        //关闭输入流
        socket.shutdownInput();
    }
}
