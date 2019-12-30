package xin.liujiajun.redis.cache.spi;

import xin.liujiajun.redis.client.serialization.Serialization;

/**
 * @author liujiajun
 * @date 2019-12-30 11:20
 **/
public interface CacheManager {


    void addCache(String region,Cache cache);

    Cache getCache(String region);

    Cache getCache(String region,Serialization serialization);

    void init();

    void destroy() throws Exception;

}
