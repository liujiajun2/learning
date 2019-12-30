package xin.liujiajun.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.util.Pool;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-12-30 13:52
 **/
public class RedisPubSubService {

    private static final Logger logger = LoggerFactory.getLogger(RedisPubSubService.class);

    @Autowired
    private RedisPubSubConnection pubSubConnection;

    public void publish(String channel,String msg){
        publish(getPoolList(),channel,msg);
    }

    public void subscribe(JedisPubSub sub,String... channels){
        if (sub == null ){
            return;
        }
        if (channels == null || channels.length <= 0){
            return ;
        }
        getSubscribePools()
                .forEach(pool->
                        new RedisSubscribeThread(pool,sub,channels).start()
                );
    }

    private void publish(List<Pool<Jedis>> pools,String channel,String msg){
        pools.forEach(p-> publish(p,channel,msg));
    }

    private void publish(Pool<Jedis> pool,String channel, String msg ){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.publish(channel,msg);
        }catch (Exception e) {
            logger.error("redis publish happen exception");
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }



    private List<Pool<Jedis>> getPoolList(){
        return pubSubConnection.getPublishPools();
    }
    private List<Pool<Jedis>> getSubscribePools() {
        return pubSubConnection.getSubscribePools();
    }

    private static class RedisSubscribeThread extends Thread{
        private String[] channels;
        private JedisPubSub sub;
        private Pool<Jedis> subPool;

        public RedisSubscribeThread( Pool<Jedis> subPool,JedisPubSub sub,String... channels){
            this.subPool = subPool;
            this.sub = sub;
            this.channels = channels;
        }
        @Override
        public void run(){
            //while true 是为了，当发生sub发生异常的时候，能够进行重试恢复
            while (true) {
                Jedis jedis = null;
                try {
                    jedis = subPool.getResource();
                    jedis.subscribe(sub,channels);
                }catch (Exception e) {
                    logger.error("redis subscribe happen exception",e);
                }finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }

                logger.info("redis subscribe sleep 5 second after , try to subscribe");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    logger.error("Thread sleep happen exception",e);
                }
            }
        }

    }
}
