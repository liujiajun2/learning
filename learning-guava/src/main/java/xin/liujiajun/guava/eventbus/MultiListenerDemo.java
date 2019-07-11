package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 22:07
 */
public class MultiListenerDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("demo");
        MultiListener multiListener = new MultiListener();
        eventBus.register(multiListener);

        eventBus.post(100);
        eventBus.post(200);
        eventBus.post(300);

        eventBus.post(1000000L);
        eventBus.post(2000000L);
        eventBus.post(3000000L);

        System.out.println("intValue " + multiListener.getIntValue() );
        System.out.println("longValue " + multiListener.getLongValue() );
    }
}
