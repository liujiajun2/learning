package xin.liujiajun.guava.collection.expend;

import com.google.common.collect.AbstractIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author liujiajun
 * @description AbstractIterator demo
 * @create 2019-03-12 17:36
 **/
public class AbstractIteratorDemo {
    public static Iterator<String> skipNulls(final Iterator<String> in){
        return new AbstractIterator<String>() {
            @Override
            protected String computeNext() {
                while (in.hasNext()){
                    String next = in.next();
                    if (next != null && !"".equals(next)) {
                        return next;
                    }
                }
                return endOfData();
            }
        };
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("fadsf", null, "", null, "dsafa");
        Iterator<String> stringIterator = skipNulls(list.iterator());
        while (stringIterator.hasNext()){
            //fadsf
            //dsafa
            System.out.println(stringIterator.next());
        }
    }
}
