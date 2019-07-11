package xin.liujiajun.guava.cache;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @description Cache demo
 * @create 2019-03-13 10:10
 **/
public class CacheDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        RemovalListener<String, String> removalListener = new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> removalNotification) {
                String key = removalNotification.getKey();
                String value = removalNotification.getValue();
                RemovalCause cause = removalNotification.getCause();
                System.out.println("remove cause is : " + cause + " key is:" + key + " value is:" + value);
            }
        };
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return "hello " + s;
            }
        };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .recordStats()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .removalListener(removalListener)
                .build(cacheLoader);

        cache.put("test", "world");
        //同时会输出 remove cause is : EXPLICIT key is:test value is:world
        cache.invalidate("test");
        //hello test 说明已经被清除了
        System.out.println(cache.get("test"));


        CacheStats stats = cache.stats();
        System.out.println(stats);


        //显式清除

        CacheLoader<String, String> cacheLoader1 = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return "hi " + s;
            }
        };

        LoadingCache<String, String> build = CacheBuilder.newBuilder().build(cacheLoader1);
        build.put("demo","demo");
        // demo
        System.out.println(build.get("demo"));
        build.invalidate("demo");
        // hi demo
        System.out.println(build.get("demo"));
    }
}
