package xin.liujiajun.guava.eventbus;

import java.util.Observable;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 21:41
 */
public class NumberObservable extends Observable {
    private int message = 0;

    public int getMessage(){
        return message;
    }

    public void setMessage(int i){
        message = i;
        setChanged();
        notifyObservers();
    }
}
