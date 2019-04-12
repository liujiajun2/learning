package xin.liujiajun.thread.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author LiuJiaJun
 * @date 2019/1/17 22:40
 */
public class ClientSocket {
    public static void main(String[] args) {
        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            client = new Socket();
            client.connect(new InetSocketAddress("localhost",8000));
            writer = new PrintWriter(client.getOutputStream(),true);
            writer.println("hello!!!");
            writer.flush();
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("from server: " + reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(writer != null) {
                writer.close();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
