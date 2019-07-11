package xin.liujiajun.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author liujiajun
 * @description Callable demo
 * @create 2019-03-13 11:27
 **/
public class CallableDemo {

    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .build();

        try {
            String demo = cache.get("demo", new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "default";
                }
            });
            //default
            System.out.println(demo);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
