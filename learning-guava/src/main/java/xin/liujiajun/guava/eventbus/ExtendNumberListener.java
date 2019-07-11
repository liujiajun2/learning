package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 10:31
 **/
public class ExtendNumberListener {

    private Number lastMessage;

    @Subscribe
    private void listen(Number number) {
        lastMessage = number;
        System.out.println("Number Message " + number);
    }

    public Number getLastMessage(){
        return lastMessage;
    }
}
