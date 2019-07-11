package xin.liujiajun.guava.collection.util;

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;


/**
 * @author liujiajun
 * @description Multimaps demo
 * @create 2019-03-12 16:56
 **/
public class MultimapsDemo {

    public static void main(String[] args) {
        ImmutableSet<String> digits = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        ImmutableListMultimap<Integer, String> index = Multimaps.index(digits, function);
        //{4=[zero, four, five, nine], 3=[one, two, six], 5=[three, seven, eight]}
        System.out.println(index);

        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Ints.asList(2,4,6));
        multimap.putAll("a", Ints.asList(4,2,1));
        multimap.putAll("c", Ints.asList(2,5,3));
        //注意我们选择的实现，因为选了TreeMultimap，得到的结果是有序的
        TreeMultimap<Integer, String> invert = Multimaps.invertFrom(multimap, TreeMultimap.create());
        // {1=[a], 2=[a, b, c], 3=[c], 4=[a, b], 5=[c], 6=[b]}
        System.out.println(invert);
    }
}
