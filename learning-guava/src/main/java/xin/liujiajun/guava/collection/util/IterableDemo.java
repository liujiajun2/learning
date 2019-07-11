package xin.liujiajun.guava.collection.util;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiajun
 * @description Iterable demo
 * @create 2019-03-12 15:13
 **/
public class IterableDemo {
    public static void main(String[] args) {
        Iterable<Integer> concat = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 6, 5));
        // [1, 2, 3, 4, 6, 5]
        System.out.println(concat);
        int frequency = Iterables.frequency(concat, 1);
        // 1
        System.out.println(frequency);
        Iterable<List<Integer>> partition = Iterables.partition(concat, 3);
        // [[1, 2, 3], [4, 6, 5]]
        System.out.println(partition);
        Integer first = Iterables.getFirst(concat, 0);
        // 1
        System.out.println(first);
        Integer last = Iterables.getLast(concat, 0);
        // 5
        System.out.println(last);
        boolean b = Iterables.elementsEqual(concat, partition);
        // false
        System.out.println(b);
        Iterable<Integer> integers = Iterables.unmodifiableIterable(concat);
        // [1, 2, 3, 4, 6, 5]
        System.out.println(integers);
        Iterable<Integer> limit = Iterables.limit(concat, 2);
        // [1, 2]
        System.out.println(limit);
        //Integer onlyElement = Iterables.getOnlyElement(concat);
        // java.lang.IllegalArgumentException: expected one element but was: <1, 2, 3, 4, 6, ...>
        //System.out.println(onlyElement);


        ArrayList<Integer> list = Lists.newArrayList(9, 8, 7, 6, 5, 4);
        boolean contains = Iterables.contains(list, 4);
        // true
        System.out.println(contains);
        Integer[] values = Iterables.toArray(list, Integer.class);
        // 6
        System.out.println(values.length);
    }
}
