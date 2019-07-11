package xin.liujiajun.guava.base;

import com.google.common.base.Preconditions;

/**
 * @author liujiajun
 * @description Preconditions Demo
 * @create 2019-03-11 15:36
 **/
public class PreconditionsDemo {

    public static void main(String[] args) {
        //java.lang.IllegalArgumentException: 抛出异常
        Preconditions.checkArgument(false,"抛出异常");
        //java.lang.NullPointerException: object(null) is null
        Preconditions.checkNotNull(null,"%s is null","object(null)");
    }
}
