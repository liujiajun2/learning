package xin.liujiajun.guava.eventbus.listener;

import java.util.Vector;

/**
 * @author liujiajun
 * @description 事件源
 * @create 2019-03-21 09:33
 **/
public class MySource {

    private int value;
    private Vector<Listener> listeners = new Vector<>();

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void setValue(int value){
        this.value = value;

        MyEvent myEvent = new MyEvent();
        myEvent.setValue(value);

        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).eventChanged(myEvent);
        }
    }
}
