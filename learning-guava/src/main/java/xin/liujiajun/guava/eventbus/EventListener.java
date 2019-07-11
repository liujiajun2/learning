package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 20:06
 */
public class EventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(EventDemo eventDemo){
        lastMessage = eventDemo.getMessage();
        System.out.println("event Message " + lastMessage);
    }


    public int getLastMessage(){
        return lastMessage;
    }
}
