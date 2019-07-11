package xin.liujiajun.guava.math;

import com.google.common.math.DoubleMath;

import java.math.RoundingMode;

/**
 * @author liujiajun
 * @description DoubleMath demo
 * @create 2019-03-14 09:53
 **/
public class DoubleMathDemo {

    public static void main(String[] args) {

        // true
        System.out.println(DoubleMath.isMathematicalInteger(13.0));
        // 13
        System.out.println(DoubleMath.roundToInt(13.2, RoundingMode.FLOOR));


    }
}
