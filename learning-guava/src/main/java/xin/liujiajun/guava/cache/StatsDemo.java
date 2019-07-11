package xin.liujiajun.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * @author liujiajun
 * @description Stats demo
 * @create 2019-03-13 14:14
 **/
public class StatsDemo {

    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .recordStats()
                .maximumSize(1000)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return s;
                    }
                });
        cache.put("t", "world");
        cache.put("a", "apple");
        cache.put("b", "banana");
        //tt
        System.out.println(cache.get("tt"));
        //world
        System.out.println(cache.get("t"));
        cache.invalidate("a");
        CacheStats stats = cache.stats();
        //0.5
        System.out.println(stats.hitRate());
        //2793672.0
        System.out.println(stats.averageLoadPenalty());
        //0
        System.out.println(stats.evictionCount());
        //CacheStats{hitCount=1, missCount=1, loadSuccessCount=1, loadExceptionCount=0, totalLoadTime=2793672, evictionCount=0}
        System.out.println(stats);
    }
}

