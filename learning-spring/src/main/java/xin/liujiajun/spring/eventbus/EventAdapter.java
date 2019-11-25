package xin.liujiajun.spring.eventbus;

/**
 * @author liujiajun
 * @date 2019-10-15 13:58
 **/
public abstract class EventAdapter<E extends BaseEvent> {

    public abstract void handle(E e);
}
