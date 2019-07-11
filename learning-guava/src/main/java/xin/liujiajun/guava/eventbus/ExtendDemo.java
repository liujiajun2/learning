package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 10:33
 **/
public class ExtendDemo {

    public static void main(String[] args) {

        //Event的继承
        //如果Listener A监听Event A ，而且Event A 有一个子类 Event B，此时Listener A 将会接受 EventA 和 B的消息
        //EventBus自动把事件分发给事件超类的监听者
        EventBus eventBus = new EventBus("extends");
        ExtendIntegerListener integerListener = new ExtendIntegerListener();
        ExtendNumberListener numberListener = new ExtendNumberListener();
        eventBus.register(integerListener);
        eventBus.register(numberListener);

        eventBus.post(100);
        eventBus.post(1000000L);
    }
}
