package xin.liujiajun.guava.eventbus.listener;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 09:37
 **/
public class Demo {

    public static void main(String[] args) {
        MySource s = new MySource();
        MyListener listener = new MyListener();

        s.addListener(listener);

        s.setValue(10);

    }
}
