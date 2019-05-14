package xin.liujiajun.socket.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author LiuJiaJun
 * @date 2019/5/8 11:43
 */
public class SocketServer {

    private static ExecutorService executor = new ThreadPoolExecutor(50,50, 60,
            TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    public static void main(String[] args){
        try(ServerSocket serverSocket = new ServerSocket(13)){
            while (true){
                try{
                    Socket socket = serverSocket.accept();
                    executor.submit(new DayTimeTask(socket));
                }catch (IOException e){

                }
            }
        }catch (IOException e){
            System.out.println("could not start server");
        }
    }

    private static class DayTimeTask implements Runnable{

        private Socket socket;

        public DayTimeTask(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                Date date = new Date();
                outputStreamWriter.write(date.toString() + "\r\n");
                outputStreamWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
