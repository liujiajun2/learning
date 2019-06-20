package xin.liujiajun.socket.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author LiuJiaJun
 * @date 2019/6/12 14:28
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9090);
        while (true) {
            Socket accept = server.accept();
            System.out.println("接受一个socket");
            new Thread(new InnerEcho(accept)).start();
        }
    }

    private static class InnerEcho implements Runnable {
        private Socket socket;

        public InnerEcho(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());

                String str = null;
                while ((str = input.readLine()) != null) {
                    output.write(str);
                    System.out.println(str);
                }
                output.flush();
                socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
