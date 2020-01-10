package xin.liujiajun.metrics.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2020-01-09 11:15
 **/
public class ThreadPoolUtil {


    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            10,
            20,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactory() {
                private AtomicInteger index = new AtomicInteger(0);
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("CorePool-" + index.incrementAndGet());
                    return thread;
                }
            },new ThreadPoolExecutor.CallerRunsPolicy());
}
