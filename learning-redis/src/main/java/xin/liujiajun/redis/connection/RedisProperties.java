package xin.liujiajun.redis.connection;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liujiajun
 * @date 2019-12-30 10:59
 **/
@Component
@ConfigurationProperties("spring.redis")
public class RedisProperties {
    /**
     * Database index used by the connection factory.
     */
    private int database = 0;

    /**
     * Redis url, which will overrule host, port and password if set.
     */
    private String url;

    /**
     * Redis server host.
     */
    private String host = "localhost";

    /**
     * Login password of the redis server.
     */
    private String password;

    /**
     * Redis server port.
     */
    private int port = 6379;

    /**
     * Enable SSL.
     */
    private boolean ssl;

    /**
     * Connection timeout in milliseconds.
     */
    private int timeout;

    private Pool pool = new Pool();

    public int getDatabase() {
        return database;
    }

    public RedisProperties setDatabase(int database) {
        this.database = database;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RedisProperties setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getHost() {
        return host;
    }

    public RedisProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RedisProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getPort() {
        return port;
    }

    public RedisProperties setPort(int port) {
        this.port = port;
        return this;
    }

    public boolean isSsl() {
        return ssl;
    }

    public RedisProperties setSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public RedisProperties setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public Pool getPool() {
        return pool;
    }

    public RedisProperties setPool(Pool pool) {
        this.pool = pool;
        return this;
    }

    /**
     * Pool properties.
     */
    public static class Pool {

        /**
         * Max number of "idle" connections in the pool. Use a negative value to indicate
         * an unlimited number of idle connections.
         */
        private int maxIdle = 8;

        /**
         * Target for the minimum number of idle connections to maintain in the pool. This
         * setting only has an effect if it is positive.
         */
        private int minIdle = 0;

        /**
         * Max number of connections that can be allocated by the pool at a given time.
         * Use a negative value for no limit.
         */
        private int maxActive = 8;

        /**
         * Maximum amount of time (in milliseconds) a connection allocation should block
         * before throwing an exception when the pool is exhausted. Use a negative value
         * to block indefinitely.
         */
        private int maxWait = -1;

        public int getMaxIdle() {
            return this.maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return this.minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return this.maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public int getMaxWait() {
            return this.maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }



    }
}
