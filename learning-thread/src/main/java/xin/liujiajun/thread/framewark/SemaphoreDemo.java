package xin.liujiajun.thread.framewark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2020-09-18 08:38
 **/
public class SemaphoreDemo {


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            pool.submit(()->{
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println((System.currentTimeMillis() / 1000) + " :complete!!");
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


    }
}
