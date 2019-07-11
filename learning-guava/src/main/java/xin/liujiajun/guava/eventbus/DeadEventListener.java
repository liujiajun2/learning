package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 22:35
 */
public class DeadEventListener {
    //如果EventBus 发送的消息否不是订阅这关心的 称之为Dead Event

    boolean delivered = false;

    @Subscribe
    public void listen(DeadEvent deadEvent){
        delivered = true;
    }

    public boolean isDelivered(){
        return delivered;
    }
}
