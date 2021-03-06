package xin.liujiajun.redis.connection;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liujiajun
 * @date 2019-12-30 10:59
 **/
@Component
@ConfigurationProperties("spring.redis")
public class RedisProperties {
    private int database = 0;
    private String url;
    private String host = "localhost";
    private String password;
    private int port = 6379;
    private boolean ssl;
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

    public static class Pool {

        private int maxIdle = 8;
        private int minIdle = 0;
        private int maxActive = 8;
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
