package xin.liujiajun.guava.collection.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multisets;

/**
 * @author liujiajun
 * @description Multisets demo
 * @create 2019-03-12 16:32
 **/
public class MultisetsDemo {

    public static void main(String[] args) {
        HashMultiset<String> multiset = HashMultiset.create();
        multiset.add("a",2);
        HashMultiset<Object> multiset1 = HashMultiset.create();
        multiset1.add("a",5);

        //返回 true,都包含 a 值
        System.out.println(multiset.containsAll(multiset1));
        //返回false multiset 的值（2） 不小于 multiset1的值（5）
        System.out.println(Multisets.containsOccurrences(multiset, multiset1));
        Multisets.removeOccurrences(multiset1,multiset);
        //[a x 3] 移除了两个 a 还剩下 3个
        System.out.println(multiset1);
        //全部移除
        System.out.println(multiset1.removeAll(multiset));
        // multiset1为空了
        System.out.println(multiset1.isEmpty());

        HashMultiset<String> set = HashMultiset.create();
        set.add("a",3);
        set.add("b",5);
        set.add("c",1);
        ImmutableMultiset<String> strings = Multisets.copyHighestCountFirst(set);
        // [b x 5, a x 3, c]
        System.out.println(strings);

    }
}
