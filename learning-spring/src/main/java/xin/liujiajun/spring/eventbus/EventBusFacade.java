package xin.liujiajun.spring.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author liujiajun
 * @date 2019-10-15 14:06
 **/
public class EventBusFacade {

    private final static EventBus eventBus = new EventBus();

    public static void post(BaseEvent event) {
        execute(event);
    }


    public static void execute(BaseEvent event) {
        if (event == null) {
            return;
        }
        eventBus.post(event);
    }


    public static void register(EventAdapter<? extends BaseEvent> handler) {
        if (handler == null) {
            return;
        }
        eventBus.register(handler);
        System.out.println("Registered eventAdapter class: " + handler.getClass());
    }


    public static void unregister(EventAdapter<? extends BaseEvent> handler) {
        if (handler == null) {
            return;
        }
        eventBus.unregister(handler);
        System.out.println("Unregisted eventAdapter class: " + handler.getClass());
    }


}
