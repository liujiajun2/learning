package xin.liujiajun.redis.test;

import org.springframework.stereotype.Component;
import xin.liujiajun.redis.cache.AbstractCache;

/**
 * @author liujiajun
 * @date 2019-12-30 12:53
 **/
@Component
public class TestCache extends AbstractCache {
    @Override
    protected String getRegion() {
        return "TEST_CACHE";
    }
}
