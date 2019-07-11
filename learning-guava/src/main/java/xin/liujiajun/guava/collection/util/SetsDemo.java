package xin.liujiajun.guava.collection.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * @author liujiajun
 * @description Sets demo
 * @create 2019-03-12 15:58
 **/
public class SetsDemo {

    public static void main(String[] args) {
        ImmutableSet<Integer> first = ImmutableSet.of(1, 2, 3, 5);
        ImmutableSet<Integer> second = ImmutableSet.of(4, 5, 6, 7);
        Sets.SetView<Integer> union = Sets.union(first, second);
        // [1, 2, 3, 5, 4, 6, 7]
        System.out.println(union);
        Sets.SetView<Integer> intersection = Sets.intersection(first, second);
        // [5]
        System.out.println(intersection);
        Sets.SetView<Integer> difference = Sets.difference(first, second);
        // [1, 2, 3]
        System.out.println(difference);
        Sets.SetView<Integer> integers = Sets.symmetricDifference(first, second);
        // [1, 2, 3, 4, 6, 7]
        System.out.println(integers);
        Set<List<Integer>> lists = Sets.cartesianProduct(first, second);
        // [[1, 4], [1, 5], [1, 6], [1, 7],...,[5, 7]]
        System.out.println(lists);

        Set<Set<Integer>> sets = Sets.powerSet(first);
        // powerSet({1=0, 2=1, 3=2, 5=3})
        System.out.println(sets);
    }
}
