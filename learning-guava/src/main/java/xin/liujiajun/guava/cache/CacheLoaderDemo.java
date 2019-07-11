package xin.liujiajun.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author liujiajun
 * @description CacheLoader demo
 * @create 2019-03-13 11:05
 **/
public class CacheLoaderDemo {

    public static void main(String[] args) {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return "hello " + s;
                    }
                });

        try {
            String test = cache.get("test");
            // hello test
            System.out.println(test);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LoadingCache<String, String> build = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s){
                        return "hello " + s;
                    }
                });
        String test = build.getUnchecked("test");
        // hello test
        System.out.println(test);

        LoadingCache<String, String> cache1 = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return "hi " + s;
                    }
                    @Override
                    public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                        Map<String, String> map = new HashMap<>(64);
                        Iterator<? extends String> iterator = keys.iterator();
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            map.put(key, "hi all " + key);
                        }
                        return map;
                    }
                });

        List<String> list = Arrays.asList("demo", "test");
        try {
            ImmutableMap<String, String> all = cache1.getAll(list);
            //{demo=hi all demo, test=hi all test}
            System.out.println(all);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
