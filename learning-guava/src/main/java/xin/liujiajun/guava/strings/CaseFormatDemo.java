package xin.liujiajun.guava.strings;

import com.google.common.base.CaseFormat;

/**
 * @author liujiajun
 * @description CaseFormat demo
 * @create 2019-03-13 15:43
 **/
public class CaseFormatDemo {


    public static void main(String[] args) {
        String str = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        //constantName
        System.out.println(str);
    }
}
