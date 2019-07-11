package xin.liujiajun.guava.eventbus.jdklistener;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 09:42
 **/
public class MySource {
    private Collection listeners;


    public void addListener(Listener listener){
        if (listeners == null) {
            listeners = new HashSet<Listener>();
        }
        listeners.add(listener);
    }

    public void remoreListener(Listener listener){
        if (listeners == null) {
            return ;
        }
        listeners.remove(listener);
    }

    public void changeValue(int value){
        MyEvent e = new MyEvent(this, value);
        notify(e);
    }
    private void notify(MyEvent event){
        Iterator iterator = listeners.iterator();
        while (iterator.hasNext()){
            Listener next = (Listener)iterator.next();
            next.eventChanged(event);
        }
    }
}
