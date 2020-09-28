package xin.liujiajun.thread.cocurrency;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author liujiajun
 * @date 2020-09-21 19:55
 **/
public class AtomicIntegerArrayDemo {

    public static void main(String[] args) {
        int[] value = {1, 2};
        AtomicIntegerArray integerArray = new AtomicIntegerArray(value);

        integerArray.getAndSet(0,3);

        System.out.println(integerArray.get(0));
        System.out.println(value[0]);

    }
}
