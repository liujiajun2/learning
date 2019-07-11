package xin.liujiajun.guava.eventbus.jdklistener;

import java.util.EventObject;

/**
 * @author liujiajun
 * @description 定义一个事件状态对象
 * @create 2019-03-21 09:40
 **/
public class MyEvent extends EventObject {

    private int value;


    public MyEvent(Object source,int value){
        super(source);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
