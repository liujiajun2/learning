package xin.liujiajun.thread.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author LiuJiaJun
 * @date 2019/1/17 23:53
 */
public class HeavySocketClient {
    private static ExecutorService tp = new ThreadPoolExecutor(0,100,6L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
    private static final long SLEEP_TIME = 1000_000_000;
    public static class EchoClient implements Runnable{
        @Override
        public void run() {
            Socket client = null;
            PrintWriter writer = null;
            BufferedReader reader = null;
            try {
                client = new Socket();
                client.connect(new InetSocketAddress("localhost",8000));
                writer = new PrintWriter(client.getOutputStream(),true);
                writer.print("H");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("E");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("L");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("L");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("O");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("!");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.println();
                writer.flush();

                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("from server: " + reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
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

    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        for (int i = 0; i < 10; i++) {
            tp.execute(echoClient);
        }
        tp.shutdown();
    }
}
