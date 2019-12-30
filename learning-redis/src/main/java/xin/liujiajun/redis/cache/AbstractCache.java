package xin.liujiajun.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import xin.liujiajun.redis.cache.spi.Cache;
import xin.liujiajun.redis.cache.spi.CacheManager;
import xin.liujiajun.redis.client.serialization.DefaultSerialication;
import xin.liujiajun.redis.client.serialization.Serialization;
import xin.liujiajun.redis.exception.CacheException;

/**
 * @author liujiajun
 * @date 2019-12-30 11:19
 **/
public abstract class AbstractCache implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCache.class);

    @Autowired(required = false)
    private CacheManager cacheManager;

    private Serialization serialization;

    protected abstract String getRegion();

    public void put(Object key, Object value) {
        try {
            getCacheImpl().put(key, value);
        } catch (CacheException e) {
            handlePutException(e, key, value);
        }
    }

    public Object get(Object key) {
        try {
            return getCacheImpl().get(key);
        } catch (CacheException e) {
            return handleGetException(e, key);
        }
    }

    public Cache getCacheImpl() {
        return cacheManager.getCache(getRegion(), getSerialization());
    }

    private Serialization getSerialization() {
        if (serialization == null) {
            serialization = createSerialization();
        }
        return serialization;
    }

    protected Serialization createSerialization() {
        return new DefaultSerialication();
    }

    protected Object handleGetException(CacheException cache, Object key) {
        System.out.println("get exception");
        return null;
    }

    protected void handlePutException(CacheException cache, Object key, Object value) {
        System.out.println("put exception");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
