package xin.liujiajun.guava.concurrency;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liujiajun
 * @description ListenableFuture demo
 * @create 2019-03-15 10:48
 **/
public class ListenableFutureDemo {
    public static void main(String[] args) {

        //JDk
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("JDK running");
                return new Object();
            }
        });
        executorService.shutdown();

        //Guava
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Object> explosion = service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("Guava Running");
                return new Object();
            }
        });
        //ListenableFuture 继承Future 并且多了一个addListener方法
        //该方法在多线程运算完的时候，指定的Runnable参数传入的对象会被指定的Executor执行
        explosion.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("Guava listener running");
            }
        },service);
        // 添加回调
        Futures.addCallback(explosion, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@Nullable Object o) {
                System.out.println("Guava callback success");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Guava callback fail");
            }
        },service);
        service.shutdown();

        //output
        //JDK running
        //Guava Running
        //Guava listener running
        //Guava callback success



    }
}
