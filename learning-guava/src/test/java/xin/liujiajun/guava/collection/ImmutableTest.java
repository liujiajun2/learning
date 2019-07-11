package xin.liujiajun.guava.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-20 08:22
 **/
public class ImmutableTest {

    @Test
    public void test01(){
        Set<String> set = new HashSet<>();
        set.add("red");
        set.add("orange");
        set.add("yellow");
        set.add("green");
        ImmutableSet<String> strings = ImmutableSet.copyOf(set);
        System.out.println(strings);
        ImmutableList<String> list = strings.asList();
        System.out.println(list);

    }

    @Test
    public void test03(){

    }

    @Test
    public void test04(){

    }

    @Test
    public void test05(){

    }

    @Test
    public void test06(){

    }

}
