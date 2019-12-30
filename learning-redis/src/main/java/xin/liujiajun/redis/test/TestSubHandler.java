package xin.liujiajun.redis.test;

import org.springframework.stereotype.Component;
import xin.liujiajun.redis.pubsub.SubscribeHandler;

/**
 * @author liujiajun
 * @date 2019-12-30 14:22
 **/
@Component
public class TestSubHandler implements SubscribeHandler {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("++++++++++");
        System.out.println("channel " + channel + ", message " + message);
        System.out.println("++++++++++");
    }
}
