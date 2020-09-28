package xin.liujiajun.thread.cocurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2020-09-21 19:43
 **/
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        AtomicInteger index = new AtomicInteger(1);

        System.out.println(index.getAndIncrement());
        System.out.println(index.get());

    }
}
