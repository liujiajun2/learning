package xin.liujiajun.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @description TimedEviction demo
 * @create 2019-03-13 11:56
 **/
public class TimedEvictionDemo {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return "hi " + s;
                    }
                });
        cache.put("test","test");
        //test
        System.out.println(cache.get("test"));
        TimeUnit.SECONDS.sleep(1);
        // hi test
        System.out.println(cache.get("test"));
        cache.put("demo","demo");
        // hi test
        System.out.println(cache.get("test"));
    }
}
