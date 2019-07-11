package xin.liujiajun.guava.strings;

import com.google.common.base.Splitter;

import java.util.Arrays;

/**
 * @author liujiajun
 * @description Splitter demo
 * @create 2019-03-13 14:36
 **/
public class SplitterDemo {

    public static void main(String[] args) {

        // JDK内建的字符串拆分工具
        String[] split = "foo,bar,,qux,".split(",");
        //输出的是 foo bar "" qux
        Arrays.asList(split).forEach(System.out::println);

        Iterable<String> strings = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .split(",foo,bar,,qux,");
        //[foo, bar, qux]
        System.out.println(strings);
    }
}
