package xin.liujiajun.guava.strings;

import com.google.common.base.Joiner;

import java.util.Arrays;

/**
 * @author liujiajun
 * @description Joiner deno
 * @create 2019-03-13 14:24
 **/
public class JoinerDemo {

    public static void main(String[] args) {

        Joiner joiner = Joiner.on(";").skipNulls();
        String str = joiner.join("Harry", null, "ron", "jack");
        //Harry;ron;jack
        System.out.println(str);

        Joiner bob = Joiner.on(";").useForNull("Bob");
        String str2 = bob.join("Harry", null, "ron", "jack");
        //Harry;Bob;ron;jack
        System.out.println(str2);

        String join = Joiner.on(",").join(Arrays.asList(1, 2, 6));
        //1,2,6
        System.out.println(join);

    }
}
