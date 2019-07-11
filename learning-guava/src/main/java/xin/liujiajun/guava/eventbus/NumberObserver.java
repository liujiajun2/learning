package xin.liujiajun.guava.eventbus;

import java.util.Observable;
import java.util.Observer;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 21:45
 */
public class NumberObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        NumberObservable observer = (NumberObservable) o;
        System.out.println("message has change " + observer.getMessage());
    }
}
