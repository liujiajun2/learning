package xin.liujiajun.redis.cache.spi.redis;

import org.springframework.beans.factory.annotation.Autowired;
import xin.liujiajun.redis.cache.spi.AbstractCacheManager;
import xin.liujiajun.redis.cache.spi.Cache;
import xin.liujiajun.redis.client.serialization.Serialization;
import xin.liujiajun.redis.connection.RedisConnection;

/**
 * @author liujiajun
 * @date 2019-12-30 11:27
 **/
public class RedisCacheManager extends AbstractCacheManager {

    @Autowired
    private RedisConnection redisConnection;

    @Override
    public Cache getCache(String region, Serialization serialization) {
        Cache cache = super.getCache(region);
        if(cache == null) {
            cache = new RedisCache(region,redisConnection.getJedisPool(),serialization);
            addCache(region,cache);
        }
        return cache;
    }

    @Override
    public void init() {
        redisConnection.init();
    }

    @Override
    public void destroy() throws Exception {
        redisConnection.destroy();
    }
}
