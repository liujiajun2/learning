package xin.liujiajun.redis.cache.spi;

import xin.liujiajun.redis.exception.CacheException;

public interface Cache {

    String getRegion();

    Object get(Object key) throws CacheException;

    void put(Object key,Object value) throws CacheException;


}
