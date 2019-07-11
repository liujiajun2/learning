package xin.liujiajun.guava.eventbus.jdklistener;

/**
 * @author liujiajun
 * @description 具体实现监听功能
 * @create 2019-03-21 09:47
 **/
public class MyListener implements Listener {
    @Override
    public void eventChanged(MyEvent event) {
        System.out.println("value changed " + event.getValue());
    }
}
