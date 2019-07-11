package xin.liujiajun.guava.strings;

import com.google.common.base.CharMatcher;

/**
 * @author liujiajun
 * @description CharMatcher demo
 * @create 2019-03-13 14:52
 **/
public class CharMatcherDemo {

    public static void main(String[] args) {

        //只保留数字字符
        String digit = CharMatcher.digit().retainFrom("dasfaf1245adfdas4234");
        // 12454234
        System.out.println(digit);
        //去除两端的空格，并把中间的连续空格替换成单个空格
        String tt = CharMatcher.whitespace().trimAndCollapseFrom(" te stt ", ';');
        // te;stt
        System.out.println(tt);

    }
}
