package xin.liujiajun.guava.base;

import com.google.common.base.Objects;

/**
 * @author liujiajun
 * @description Object common method demo
 * @create 2019-03-11 17:18
 **/
public class ObjectDemo {

    public static void main(String[] args) {
        //true
        boolean e = Objects.equal("a", "a");
        //false
        boolean e1 = Objects.equal(null, "a");
        //false
        boolean e2 = Objects.equal("a", null);
        //true
        boolean e3 = Objects.equal(null, null);

        int i = Objects.hashCode("a", "b", "c");
        System.out.println(i);
    }
}
