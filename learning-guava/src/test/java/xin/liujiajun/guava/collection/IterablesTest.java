package xin.liujiajun.guava.collection;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-20 09:04
 **/
public class IterablesTest {


    @Test
    public void test(){

        Iterable<Integer> concat = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6));
        int size = Iterables.size(concat);

        new Comparable<Integer>(){
            @Override
            public int compareTo(Integer o) {
                return 0;
            }
        };

        new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
    }
}
