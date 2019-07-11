package xin.liujiajun.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @description Refresh Demo
 * @create 2019-03-13 14:04
 **/
public class RefreshDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return s;
            }

            @Override
            public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "new " + oldValue;
                    }
                });
                task.run();
                return task;
            }
        };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(cacheLoader);
        cache.put("t","tttt");
        //tttt
        System.out.println(cache.get("t"));
        TimeUnit.SECONDS.sleep(1);
        // new tttt
        System.out.println(cache.get("t"));
    }
}
