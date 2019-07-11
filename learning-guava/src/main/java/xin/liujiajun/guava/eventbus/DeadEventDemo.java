package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 22:38
 */
public class DeadEventDemo {

    public static void main(String[] args) {

        //learning url
        //https://www.cnblogs.com/peida/p/EventBus.html

        EventBus eventBus = new EventBus("demo");

        DeadEventListener deadEventListener = new DeadEventListener();

        eventBus.register(deadEventListener);

        eventBus.post(1);
        eventBus.post(2);

        System.out.println("deadEvent " + deadEventListener.isDelivered());
    }
}
