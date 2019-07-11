package xin.liujiajun.guava.collection.util;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * @author liujiajun
 * @description Lists demo
 * @create 2019-03-12 15:48
 **/
public class ListsDemo {

    public static void main(String[] args) {
        List<Integer> values = Ints.asList(1, 2, 3, 4, 5);
        List<Integer> reverse = Lists.reverse(values);
        // [5, 4, 3, 2, 1]
        System.out.println(reverse);
        List<List<Integer>> partition = Lists.partition(values, 2);
        // [[1, 2], [3, 4], [5]]
        System.out.println(partition);
    }
}
