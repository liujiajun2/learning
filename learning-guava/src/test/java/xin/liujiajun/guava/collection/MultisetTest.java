package xin.liujiajun.guava.collection;

import com.google.common.collect.HashMultiset;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-20 08:22
 **/
public class MultisetTest {

    @Test
    public void testMultiset(){

        HashMultiset<String> hash = HashMultiset.create();

        hash.add("a");
        hash.add("a");
        hash.add("b");
        hash.add("b");
        hash.add("c");

        Assert.assertEquals(2,hash.count("a"));

    }
}
