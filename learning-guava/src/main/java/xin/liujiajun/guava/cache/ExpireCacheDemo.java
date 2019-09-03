package xin.liujiajun.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-08-26 15:56
 **/
public class ExpireCacheDemo {

    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, Long> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Long>() {
                    @Override
                    public Long load(String s) throws Exception {
                        return 0L;
                    }
                });

        cache.put("123",System.currentTimeMillis());
        cache.put("567",System.currentTimeMillis());

        TimeUnit.SECONDS.sleep(5);

        Long start = null;
        try {
            start = cache.get("123");
        } catch (ExecutionException e) {

        }
        if (start == null) {
            System.out.println("null");
        }
        if (System.currentTimeMillis() - start >= 5000) {
            System.out.println("timeout");
        }
        System.out.println("ok");
    }
}
