package xin.liujiajun.guava.collection.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liujiajun
 * @description immutable demo
 * @create 2019-03-12 09:45
 **/
public class ImmutableDemo {

    public static void main(String[] args) {
        //方式一：copyOf方法。如ImmutableSet.copyOf(set)
        Set<String> set = new HashSet<>();
        set.add("red");
        set.add("orange");
        set.add("yellow");
        set.add("green");
        ImmutableSet<String> strings = ImmutableSet.copyOf(set);
        //方式二：of方法
        ImmutableSet<String> of = ImmutableSet.of("red", "orange", "yellow", "green", "blue", "purple");
        //方式三：Builder工具
        ImmutableSet<String> build = ImmutableSet.<String>builder().add("red").add("orange").add("yellow").add("green").build();


        ImmutableSortedSet<String> sortedSet = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        //[a, b, c, d]
        System.out.println(sortedSet);

        ImmutableList<String> list = sortedSet.asList();
        //[a, b, c, d]
        System.out.println(list);
    }
}
