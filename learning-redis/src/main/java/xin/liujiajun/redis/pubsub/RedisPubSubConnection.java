package xin.liujiajun.redis.pubsub;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author liujiajun
 * @date 2019-12-30 13:37
 **/
public class RedisPubSubConnection implements InitializingBean, DisposableBean {

    @Autowired
    private RedisPubSubProperties redisPubSubProperties;

    private List<Pool<Jedis>> publishPools = new ArrayList<>();

    private List<Pool<Jedis>> subscribePools = new ArrayList<>();

    private static final String SPLIT_STR = ",";

    public List<Pool<Jedis>> getPublishPools() {
        return publishPools;
    }

    public List<Pool<Jedis>> getSubscribePools() {
        return subscribePools;
    }

    public synchronized void init() {
        initPublishPool();
        initSubscrivePool();
    }


    private void initPublishPool() {
        String host = redisPubSubProperties.getHost();
        String[] hostList = host.split(SPLIT_STR);
        Stream.of(hostList).forEach(h -> this.publishPools.add(createJedisPool(h)));
    }

    private void initSubscrivePool() {
        String host = redisPubSubProperties.getHost();
        String[] hostList = host.split(SPLIT_STR);
        Stream.of(hostList).forEach(currHost -> this.subscribePools.add(createJedisPool(currHost)));
    }

    private JedisPoolConfig getJedisPoolConfig(RedisPubSubProperties redisPubSubProperties) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        if (redisPubSubProperties.getPool() != null) {
            RedisPubSubProperties.Pool pool = redisPubSubProperties.getPool();
            jedisPoolConfig.setMaxTotal(pool.getMaxActive());
            jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
            jedisPoolConfig.setMinIdle(pool.getMinIdle());
            jedisPoolConfig.setMaxWaitMillis(pool.getMaxWait());
        }
        return jedisPoolConfig;
    }

    private Pool<Jedis> createJedisPool(String host) {
        if (redisPubSubProperties.getPassword() == null || "".equals(this.redisPubSubProperties.getPassword())) {
            return new JedisPool(getJedisPoolConfig(redisPubSubProperties),
                    host,
                    redisPubSubProperties.getPort(),
                    redisPubSubProperties.getTimeout());
        } else {
            return new JedisPool(getJedisPoolConfig(redisPubSubProperties),
                    host,
                    redisPubSubProperties.getPort(),
                    redisPubSubProperties.getTimeout(),
                    redisPubSubProperties.getPassword(),
                    redisPubSubProperties.getTimeout());
        }
    }

    @Override
    public void destroy() throws Exception {
        this.subscribePools.forEach(Pool::destroy);
        this.publishPools.forEach(Pool::destroy);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
