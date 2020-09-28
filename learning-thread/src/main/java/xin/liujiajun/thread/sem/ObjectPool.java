package xin.liujiajun.thread.sem;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author liujiajun
 * @date 2020-08-13 13:38
 **/
public class ObjectPool<T,R> {

    private final List<T> pool;
    private final Semaphore sem;

    public ObjectPool(int size, T t) {
        //修改成ArrayList会有问题，因为存在并发操作remove、add,ArrayList下，该容器不是线程安全的。
        this.pool = new Vector<>();
        for (int i = 0; i < size; i++) {
            pool.add((T) new Object());
        }
        sem = new Semaphore(size);
    }

    public R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }


    public static void main(String[] args) {
        ObjectPool<Object, String> pool = new ObjectPool<>(10, 2L);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        AtomicInteger index = new AtomicInteger(0);
        for (int i = 0; i < 20; i++) {

            executorService.execute(()->{
                try {
                    pool.exec(t -> {
                        System.out.println( index.incrementAndGet() + ": " + t);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return t.toString();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

    }
}
