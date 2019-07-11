package xin.liujiajun.guava.base;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @author liujiajun
 * @description Ordering demo
 * @create 2019-03-12 08:27
 **/
public class OrderingDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "f", "e", "dfadf");
        //[a, f, e, dfadf]
        System.out.println(list);
        list.sort(Ordering.<String>natural());
        //[a, dfadf, e, f]
        System.out.println(list);

        List<String> aList = Arrays.asList("aaa", "bbbb", "ccccc", "d");
        Ordering<String> ordering = new Ordering<String>() {
            @Override
            public int compare(@Nullable String s, @Nullable String t1) {
                return Ints.compare(s.length(), t1.length());
            }
        };
        aList.sort(ordering);
        // [d, aaa, bbbb, ccccc]
        System.out.println(aList);

        //链式调用
        Ordering<Foo> fooOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Foo foo) {
                return foo.sortedBy;
            }
        });
        List<Foo> foos = Arrays.asList(new Foo(null, 2), new Foo("ad", 3), new Foo("hsg", 1));
        foos.sort(fooOrdering);
        //[Foo{sortedBy='null', notSortedBy=2}, Foo{sortedBy='ad', notSortedBy=3}, Foo{sortedBy='hsg', notSortedBy=1}]
        System.out.println(foos);


        List<Integer> bList = Arrays.asList(10, 3, 4, 23, 4, 9, 11, 0);
        Ordering<Integer> ordering1 = new Ordering<Integer>() {
            @Override
            public int compare(@Nullable Integer integer, @Nullable Integer t1) {
                return integer.compareTo(t1);
            }
        };
        Integer max = ordering1.max(bList);
        List<Integer> maxs = ordering1.greatestOf(bList, 2);
        //23
        System.out.println(max);
        //[23, 11]
        System.out.println(maxs);
    }


}
