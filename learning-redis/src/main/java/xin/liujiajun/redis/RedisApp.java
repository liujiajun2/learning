package xin.liujiajun.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import xin.liujiajun.redis.cache.spi.CacheManager;
import xin.liujiajun.redis.cache.spi.redis.RedisCacheManager;
import xin.liujiajun.redis.connection.RedisConnection;
import xin.liujiajun.redis.pubsub.RedisPubSubConnection;
import xin.liujiajun.redis.pubsub.RedisPubSubService;

/**
 * @author liujiajun
 * @date 2019-12-30 08:34
 **/
@SpringBootApplication
public class RedisApp {

    @Bean
    @Order(1)
    public RedisConnection redisConnection(){
        return new RedisConnection();
    }

    @Bean
    @Order(2)
    public CacheManager redisCacheManager(){
        return new RedisCacheManager();
    }


    @Bean
    @Order(1)
    public RedisPubSubConnection redisPubSubConnection(){
        return new RedisPubSubConnection();
    }

    @Bean
    @Order(2)
    public RedisPubSubService redisPubSubService(){
        return new RedisPubSubService();
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class,args);
    }
}
