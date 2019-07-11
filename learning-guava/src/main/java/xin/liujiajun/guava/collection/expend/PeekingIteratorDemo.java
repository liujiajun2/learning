package xin.liujiajun.guava.collection.expend;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * @author liujiajun
 * @description PeekingIteratorDemo demo
 * @create 2019-03-12 17:31
 **/
public class PeekingIteratorDemo {
    public static void main(String[] args) {
        List<Integer> integers = Ints.asList(1, 2, 2, 3, 4, 5, 5, 5, 5);
        List<Integer> result = Lists.newArrayList();
        PeekingIterator<Integer> iter = Iterators.peekingIterator(integers.iterator());
        while (iter.hasNext()){
            Integer current = iter.next();
            // 跳过重复的元素
            while (iter.hasNext() && iter.peek().equals(current)) {
                iter.next();
            }
            result.add(current);
        }
        // [1, 2, 3, 4, 5]
        System.out.println(result);
    }
}
