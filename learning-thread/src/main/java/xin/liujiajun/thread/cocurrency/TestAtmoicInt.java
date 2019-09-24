package xin.liujiajun.thread.cocurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2019-09-24 11:13
 **/
public class TestAtmoicInt {

    private volatile static AtomicInteger   conn = new AtomicInteger(0);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        TestAtmoicInt testAtmoicInt = new TestAtmoicInt();

        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                testAtmoicInt.dec();
            } else {
                testAtmoicInt.inc();
            }

        }


        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(testAtmoicInt.get());
        }

    }

    public int get() {
        return conn.get();
    }

    public void inc() {
        conn.incrementAndGet();
    }

    public void dec() {
        conn.decrementAndGet();
    }
}
