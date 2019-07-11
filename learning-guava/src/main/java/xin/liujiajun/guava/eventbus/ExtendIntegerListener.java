package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 10:32
 **/
public class ExtendIntegerListener {

    private Integer lastMessage;

    @Subscribe
    private void listen(Integer number) {
        lastMessage = number;
        System.out.println("Integer Message " + number);
    }

    public Integer getLastMessage(){
        return lastMessage;
    }
}
