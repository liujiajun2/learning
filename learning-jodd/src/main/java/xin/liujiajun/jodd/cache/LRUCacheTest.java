package xin.liujiajun.jodd.cache;


import jodd.cache.LRUCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-22 14:59
 **/
public class LRUCacheTest {

    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(10000);
        ExecutorService executorService = Executors.newFixedThreadPool(20, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("demo");
                return thread;
            }
        });

        executorService.submit(()->{
            for (int i = 0; i < MAX; i++) {
                cache.put(i, String.valueOf(i));
            }
        });
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                int j = 0;
                while (j<MAX){
                    System.out.println(Thread.currentThread().getName() + "   "+cache.get(j));
                    j++;
                }
            });
        }
    }
}
