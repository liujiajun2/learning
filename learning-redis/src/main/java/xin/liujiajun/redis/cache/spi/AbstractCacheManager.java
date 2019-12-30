package xin.liujiajun.redis.cache.spi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liujiajun
 * @date 2019-12-30 11:28
 **/
public abstract class AbstractCacheManager implements CacheManager {
    private final Map<String,Cache> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void addCache(String region, Cache cache) {
        this.cacheMap.put(region,cache);
    }

    @Override
    public Cache getCache(String region) {
        return this.cacheMap.get(region);
    }

}
