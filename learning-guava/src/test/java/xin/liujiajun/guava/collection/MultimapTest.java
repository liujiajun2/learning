package xin.liujiajun.guava.collection;

import com.google.common.collect.HashMultimap;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-20 08:39
 **/
public class MultimapTest {


    @Test
    public void test(){

        HashMultimap<String, String> hash = HashMultimap.create();

        hash.put("a","aa");
        hash.put("a","aaa");
        hash.put("b","b");

        Set<String> a = hash.get("a");
        System.out.println(a);

        Set<String> c = hash.get("c");
        System.out.println(c);

        Collection<String> d = hash.asMap().get("d");
        System.out.println(d);
    }
}
