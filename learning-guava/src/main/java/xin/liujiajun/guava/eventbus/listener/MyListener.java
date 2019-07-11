package xin.liujiajun.guava.eventbus.listener;

/**
 * @author liujiajun
 * @description  定义一个监听器
 * @create 2019-03-21 09:36
 **/
public class MyListener implements Listener{


    @Override
    public void eventChanged(MyEvent e) {
        System.out.println("value changed  " + e.getValue() );
    }
}
