package xin.liujiajun.guava.eventbus.jdklistener;

import java.util.EventListener;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 09:41
 **/
public interface Listener extends EventListener {

    void eventChanged(MyEvent event);

}
