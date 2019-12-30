package xin.liujiajun.redis.cache.spi.redis;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;
import xin.liujiajun.redis.cache.spi.Cache;
import xin.liujiajun.redis.client.RedisClient;
import xin.liujiajun.redis.client.serialization.Serialization;
import xin.liujiajun.redis.exception.CacheException;
import xin.liujiajun.redis.exception.RedisException;

/**
 * @author liujiajun
 * @date 2019-12-30 11:20
 **/
public class RedisCache implements Cache {

    private RedisClient redisClient;

    public RedisCache(String region, Pool<Jedis> jedisPool, Serialization serialization){
        this.redisClient = new RedisClient(region,jedisPool,serialization);
    }

    @Override
    public String getRegion() {
        return redisClient.getRegion();
    }

    @Override
    public Object get(Object key) throws CacheException {
        try{
            return redisClient.get(key);
        }catch (RedisException e ) {
            throw new CacheException(e);
        }
    }

    @Override
    public void put(Object key, Object value) throws CacheException {
        try{
            redisClient.set(key, value);
        }catch (RedisException e ) {
            throw new CacheException(e);
        }
    }
}
