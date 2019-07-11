package xin.liujiajun.guava.collection.newconllection;

import com.google.common.collect.HashMultiset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujiajun
 * @description Multiset demo
 * @create 2019-03-12 10:51
 **/
public class MultisetDemo {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a", "b", "c", "a", "b", "a", "c", "d");
        //传统方式
        Map<String, Integer> counts = new HashMap<>(16);
        for (String word: list) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word,1);
            }else {
                counts.put(word,count +1);
            }
            // jdk1.8 可以写成这样  counts.merge(word, 1, (a, b) -> a + b);
        }
        // 3
        System.out.println(counts.get("a"));

        //multiset
        HashMultiset<String> strings1 = HashMultiset.create(list);
        // 3
        System.out.println(strings1.count("a"));
    }
}
