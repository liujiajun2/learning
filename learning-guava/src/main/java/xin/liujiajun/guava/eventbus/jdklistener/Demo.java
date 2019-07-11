package xin.liujiajun.guava.eventbus.jdklistener;

/**
 * @author liujiajun
 * @description 测试
 * @create 2019-03-21 09:54
 **/
public class Demo {

    public static void main(String[] args) {


        //1.event object：事件状态对象，用于listener的相应的方法之中，作为参数，一般存在与listerner的方法之中

        //2.event source：具体的事件源，比如说，你点击一个button，那么button就是event source，要想使button对某些事件进行响应，你就需要注册特定的listener。

        //3.event listener：具体的对监听的事件类，当它监听到event object产生的时候，它就调用相应的方法，进行处理。

        MySource s = new MySource();
        MyListener myListener = new MyListener();

        s.addListener(myListener);
        s.changeValue(1);
        s.changeValue(2);
        s.changeValue(3);

    }
}
