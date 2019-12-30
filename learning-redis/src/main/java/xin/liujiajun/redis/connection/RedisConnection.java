package xin.liujiajun.redis.connection;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

/**
 * @author liujiajun
 * @date 2019-12-30 10:57
 **/
public class RedisConnection implements InitializingBean, DisposableBean {

    protected Pool<Jedis> jedisPool;

    @Autowired
    private RedisProperties redisProperties;

    protected volatile boolean started;

    public void init(){
        if (!started) {
            synchronized (this) {
                started = true;
                initJedisPool();
            }
        }
    }

    public Pool<Jedis> getJedisPool() {
        if (!started) {
            init();
        }
        return jedisPool;
    }

    protected void initJedisPool(){
        jedisPool = new JedisPool(getJedisPoolConfig(),
                this.redisProperties.getHost(),
                this.redisProperties.getPort(),
                this.redisProperties.getTimeout()
//                this.redisProperties.getPassword(),
//                this.redisProperties.getDatabase()
        );
    }

    protected JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        if (redisProperties != null) {
            RedisProperties.Pool pool = this.redisProperties.getPool();
            jedisPoolConfig.setMaxTotal(pool.getMaxActive());
            jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
            jedisPoolConfig.setMinIdle(pool.getMinIdle());
            jedisPoolConfig.setMaxWaitMillis(pool.getMaxWait());
        }
        return jedisPoolConfig;
    }
    @Override
    public void destroy() throws Exception {
        jedisPool.destroy();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }




}
