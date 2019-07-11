package xin.liujiajun.guava.collection.expend;

import com.google.common.collect.AbstractSequentialIterator;

import java.util.Iterator;

/**
 * @author liujiajun
 * @description AbstractSequentialIterator demo
 * @create 2019-03-12 17:47
 **/
public class AbstractSequentialIteratorDemo {

    public static void main(String[] args) {
        Iterator<Integer> powersOfTwo = new AbstractSequentialIterator<Integer>(1) {
            @Override
            protected Integer computeNext(Integer previous) {
                return (previous == 1 << 30) ? null : previous * 2;
            }
        };
    }
}
