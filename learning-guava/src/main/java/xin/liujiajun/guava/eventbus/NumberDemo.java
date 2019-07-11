package xin.liujiajun.guava.eventbus;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 21:49
 */
public class NumberDemo {

    public static void main(String[] args) {

        NumberObservable number = new NumberObservable();
        number.addObserver(new NumberObserver());
        number.setMessage(1);
        number.setMessage(2);
        number.setMessage(3);
    }
}
