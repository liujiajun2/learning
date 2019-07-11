package xin.liujiajun.guava.eventbus.listener;

/**
 * @author liujiajun
 * @description  定义一个自己的事件
 * @create 2019-03-21 09:31
 **/
public class MyEvent {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
