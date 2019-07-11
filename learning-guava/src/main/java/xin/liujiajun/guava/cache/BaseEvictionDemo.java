package xin.liujiajun.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

import java.util.concurrent.ExecutionException;

/**
 * @author liujiajun
 * @description BaseEviction demo
 * @create 2019-03-13 11:41
 **/
public class BaseEvictionDemo {

    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return "hi " + s;
                    }
                });
        cache.put("test1","test1");
        //test1
        System.out.println(cache.get("test1"));

        cache.put("test2","test2");
        cache.put("test3","test3");
        cache.put("test4","test4");
        // hi test1 可见 test1 被清除了
        System.out.println(cache.get("test1"));
        //test4
        System.out.println(cache.get("test4"));

        LoadingCache<String, String> build = CacheBuilder.newBuilder()
                .maximumWeight(3)
                .weigher(new Weigher<String, String>() {
                    @Override
                    public int weigh(String s, String s2) {
                        return s2.length();
                    }
                }).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return "hi " + s;
                    }
                });

        build.put("a","a");
        //a
        System.out.println(build.get("a"));
        build.put("b","bb");
        build.put("c","ccc");
        //ccc
        System.out.println(build.get("c"));
        build.put("d","dd");
        //hi c
        System.out.println(build.get("c"));
        // dd
        System.out.println(build.get("d"));
    }
}
