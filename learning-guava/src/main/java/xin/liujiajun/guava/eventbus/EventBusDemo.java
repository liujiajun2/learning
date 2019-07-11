package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 20:07
 */
public class EventBusDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("demo");
        EventListener listener = new EventListener();
        eventBus.register(listener);

        eventBus.post(new EventDemo(200));
        eventBus.post(new EventDemo(300));
        eventBus.post(new EventDemo(400));

        System.out.println("LastMessage " + listener.getLastMessage());
    }
}
