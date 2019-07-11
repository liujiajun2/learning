package xin.liujiajun.guava.collection.util;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;

/**
 * @author liujiajun
 * @description Maps demo
 * @create 2019-03-12 16:07
 **/
public class MapsDemo {

    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList("aa", "fdasf", "ccc", "dddd");
        ImmutableMap<Integer, String> strings = Maps.uniqueIndex(list, new Function<String, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable String s) {
                return s.length();
            }
        });
        //{2=aa, 5=fdasf, 3=ccc, 4=dddd}
        System.out.println(strings);

        ImmutableMap<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        ImmutableMap<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> difference = Maps.difference(left, right);

        // {b=2}
        System.out.println(difference.entriesInCommon());
        // {c=(3, 4)}
        System.out.println(difference.entriesDiffering());
        // {a=1}
        System.out.println(difference.entriesOnlyOnLeft());
        // {d=5}
        System.out.println(difference.entriesOnlyOnRight());
    }
}
