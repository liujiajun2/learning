package xin.liujiajun.redis.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;
import xin.liujiajun.redis.pubsub.RedisPubSubService;
import xin.liujiajun.redis.pubsub.SubscribeHandler;

import java.util.List;

/**
 * @author liujiajun
 * @date 2019-12-30 14:18
 **/
@Component
public class TestPubSub implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private RedisPubSubService pubSubService;

    @Autowired
    private List<SubscribeHandler> handlers;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (this.pubSubService != null) {
            pubSubService.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    super.onMessage(channel, message);
                    if (handlers != null) {
                        handlers.forEach((h)->h.onMessage(channel,message));
                    }
                }
            },"TEST");
        }

        pubSubService.publish("TEST","hello world");
    }
}
