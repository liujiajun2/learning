package xin.liujiajun.java.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2020-11-12 10:20
 **/
public class VolatileTest {


    public static void main(String[] args) throws InterruptedException {
//        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleAtFixedRate(()->{
//            System.out.println("flag=" + VolatileObj.isFlag());
//        },0,1000,TimeUnit.MILLISECONDS);
        AtomicInteger index = new AtomicInteger(0);
        Thread thread = new Thread(() -> {
            while (!VolatileObj.isFlag()) {
//                System.out.println("flag=" + VolatileObj.isFlag());
//                index.incrementAndGet();
            }
        });
        thread.start();

        TimeUnit.MILLISECONDS.sleep(1000);

//        new Thread(()->{ VolatileObj.setFlag(true);}).start();
        VolatileObj.setFlag(true);
//        thread.join();

        System.out.println("end=" + index.get());
    }
}
