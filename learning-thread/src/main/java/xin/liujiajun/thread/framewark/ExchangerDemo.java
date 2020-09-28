package xin.liujiajun.thread.framewark;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liujiajun
 * @date 2020-09-21 08:37
 **/
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.execute(()->{
            String a = "A";
            try {
                String bMsg = exchanger.exchange(a);
                System.out.println(Thread.currentThread().getName() + ",a=" + a + ",b=" + bMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(()->{
            String b = "B";
            try {
                String aMsg = exchanger.exchange(b);
                System.out.println(Thread.currentThread().getName() + ",a=" + aMsg + ",b=" + b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        pool.shutdown();

    }
}
