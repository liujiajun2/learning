package xin.liujiajun.java.common;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestMath {
    @Test
    public void test(){
        //整型BingInteger 比Integer数字范围大得多
        //浮点型 BigDecimal

        BigInteger bigInteger = new BigInteger("123456781212");
        BigDecimal bigDecimal = new BigDecimal("12345.374");
        BigDecimal bigDecimal1 = new BigDecimal("11");
        System.out.println(bigDecimal.divide(bigDecimal1,BigDecimal.ROUND_HALF_UP));//四舍五入
        System.out.println(bigDecimal.divide(bigDecimal1,15,BigDecimal.ROUND_HALF_UP));//15位
    }
}
