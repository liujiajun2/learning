package xin.liujiajun.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 15:03
 **/
public class CacheTest {
    @Test
    public void test01(){
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

    }

    @Test
    public void test03(){
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

    @Test
    public void test04() throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return "hi " + s;
                    }
                });
        cache.put("test1","test1");
//test1  异常在方法声明出抛出了
        System.out.println(cache.get("test1"));

        cache.put("test2","test2");
        cache.put("test3","test3");
        cache.put("test4","test4");
// hi test1 可见 test1 被清除了
        System.out.println(cache.get("test1"));
//test4
        System.out.println(cache.get("test4"));

    }

    @Test
    public void test05() throws ExecutionException, InterruptedException {
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

    @Test
    public void test06(){

    }

}
