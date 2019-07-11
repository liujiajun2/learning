package xin.liujiajun.guava.collection.newconllection;

import com.google.common.collect.ArrayListMultimap;

import java.util.Arrays;
import java.util.List;

/**
 * @author liujiajun
 * @description Multimap demo
 * @create 2019-03-12 11:24
 **/
public class MultimapDemo {

    public static void main(String[] args) {
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a",1);
        multimap.put("a",2);
        multimap.put("a",4);
        multimap.put("a",4);
        multimap.put("b",3);
        multimap.put("c",5);
        List<Integer> a = multimap.get("a");
        //[1, 2, 4, 4]
        System.out.println(a);
        a.remove(1);
        //{a=[1, 4, 4], b=[3], c=[5]}
        System.out.println(multimap);
        //[3]
        System.out.println(multimap.get("b"));
        //[]
        System.out.println(multimap.get("d"));
        List<Integer> c = multimap.replaceValues("c", Arrays.asList(7, 8, 9));
        //[5]
        System.out.println(c);
        //[7, 8, 9]
        System.out.println(multimap.get("c"));
    }
}
