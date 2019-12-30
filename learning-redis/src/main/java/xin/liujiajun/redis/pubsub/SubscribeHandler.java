package xin.liujiajun.redis.pubsub;

/**
 * @author liujiajun
 * @date 2019-12-30 14:18
 **/
public interface SubscribeHandler {

    void onMessage(String channel,String message);
}
