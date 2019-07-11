package xin.liujiajun.guava.math;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-14 09:34
 **/
public class IntMathDemo {


    public static void main(String[] args) {
        //java.lang.ArithmeticException: overflow: checkedAdd(2147483647, 2147483647)
        //int i = IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE);

        int sqrt = IntMath.sqrt(5, RoundingMode.DOWN);
        int sqrt1 = IntMath.sqrt(5, RoundingMode.UP);
        int divide = IntMath.divide(7, 3, RoundingMode.CEILING);
        //2
        System.out.println(sqrt);
        //3
        System.out.println(sqrt1);
        //3
        System.out.println(divide);

        // 6
        System.out.println(IntMath.gcd(18, 24));
        // 13 % 3 = 1
        System.out.println(IntMath.mod(13, 3));
        // 2 ^3 = 8
        System.out.println(IntMath.pow(2, 3));
        //true
        System.out.println(IntMath.isPowerOfTwo(1024));
        //24 = 1 * 2 * 3 * 4
        System.out.println(IntMath.factorial(4));

    }
}
