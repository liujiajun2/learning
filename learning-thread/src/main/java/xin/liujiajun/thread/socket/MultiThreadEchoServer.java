package xin.liujiajun.thread.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuJiaJun
 * @date 2019/1/17 22:20
 */
public class MultiThreadEchoServer {
    private static ExecutorService tp = new ThreadPoolExecutor(0, 10, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    static class HandleMsg implements Runnable{
        Socket clientSocket;
        public HandleMsg(Socket socket){
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;
            try{
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(),true);
                String inputLine = null;
                long start = System.currentTimeMillis();
                while ((inputLine = is.readLine()) != null) {
                    os.println(inputLine);
                }
                long end = System.currentTimeMillis();
                System.out.println("spend :" + (end - start) + "ms");
            } catch (IOException e) {
                try {
                    if(is != null){
                        is.close();
                    }
                    if(os != null){
                        os.close();
                    }
                    if(clientSocket != null){
                        clientSocket.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        ServerSocket echoServer = null;
        try {
            echoServer = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try{
                Socket clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " is connected!!!");
                tp.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
