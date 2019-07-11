package xin.liujiajun.guava.eventbus;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 20:04
 */
public class EventDemo {

    private final int message;

    public EventDemo(int message){
        this.message = message;
    }
    public int getMessage(){
        return this.message;
    }
}
