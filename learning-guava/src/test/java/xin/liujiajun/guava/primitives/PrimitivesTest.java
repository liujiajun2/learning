package xin.liujiajun.guava.primitives;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 15:06
 **/
public class PrimitivesTest {
    @Test
    public void test01(){
        ConcurrentLinkedQueue<String> strings = new ConcurrentLinkedQueue<>();
        LinkedBlockingQueue<String> strings1 = new LinkedBlockingQueue<>();
    }

    @Test
    public void test03(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        //true
        System.out.println(c == d );
        // false
        System.out.println(e ==f );
        //false  (true)
        System.out.println(c == (a + b));
        //true
        System.out.println(c.equals(a + b));
        //false  (true)
        System.out.println(g == (a + b));
        //true  (false)
        System.out.println(g.equals(a + b));

        new AtomicInteger();

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
