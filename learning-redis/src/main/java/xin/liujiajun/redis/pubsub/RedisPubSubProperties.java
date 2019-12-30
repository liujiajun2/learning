package xin.liujiajun.redis.pubsub;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liujiajun
 * @date 2019-12-30 13:33
 **/
@Component
@ConfigurationProperties("spring.redis.pubsub")
public class RedisPubSubProperties {

    private int database = 0;
    private String host = "localhost";
    private String password;
    private int port = 6379;
    private int timeout;

    private Pool pool;

    public int getDatabase() {
        return database;
    }

    public RedisPubSubProperties setDatabase(int database) {
        this.database = database;
        return this;
    }

    public String getHost() {
        return host;
    }

    public RedisPubSubProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RedisPubSubProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getPort() {
        return port;
    }

    public RedisPubSubProperties setPort(int port) {
        this.port = port;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public RedisPubSubProperties setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public Pool getPool() {
        return pool;
    }

    public RedisPubSubProperties setPool(Pool pool) {
        this.pool = pool;
        return this;
    }

    public static class Pool{
        private int maxIdle = 8;
        private int minIdle = 0;
        private int maxActive = 8;
        private int maxWait = -1;

        public int getMaxIdle() {
            return maxIdle;
        }

        public Pool setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
            return this;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public Pool setMinIdle(int minIdle) {
            this.minIdle = minIdle;
            return this;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public Pool setMaxActive(int maxActive) {
            this.maxActive = maxActive;
            return this;
        }

        public int getMaxWait() {
            return maxWait;
        }

        public Pool setMaxWait(int maxWait) {
            this.maxWait = maxWait;
            return this;
        }
    }
}
