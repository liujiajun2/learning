package xin.liujiajun.guava.primitives;

import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInts;

import java.util.List;

/**
 * @author LiuJiaJun
 * @date 2019/3/14 20:18
 */
public class Demo {

    public static void main(String[] args) {

        //常用工具
        //把数组转为相应包装类的List
        List<Integer> integers = Ints.asList(1, 2, 3, 4);
        List<Float> floats = Floats.asList(1, 2, 3, 4);

        int[] ints = {1, 2, 3};
        //串联多个原生类型数组
        int[] concat = Ints.concat(ints, new int[]{5, 6, 7});
        //判断原生类型数组是否包含给定值
        boolean contains = Ints.contains(new int[]{4, 5, 6}, 9);
        //false
        System.out.println(contains);
        // 把数组用给定分隔符连接为字符串
        String join = Ints.join(",", new int[]{1, 2, 3});
        //1,2,3
        System.out.println(join);
        //数组中最小的值
        int min = Ints.min(new int[]{5, 6, 7, 8, 9});
        //5
        System.out.println(min);
        //数组中最大的值
        int max = Ints.max(new int[]{4, 5, 6, 7, 8});
        //8
        System.out.println(max);
        //传统的Comparator.compare方法，但针对原生类型。JDK7的原生类型包装类也提供这样的方法
        int compare = Ints.compare(1, 2);
        //-1 （-1小于  等于 0  大于 1）
        System.out.println(compare);


        //字节转换方法
        //常量：表示该原生类型需要的字节数
        int bytes = Ints.BYTES;
        //接受Prims.BYTES个字节参数，按大字节序返回原生类型值
        int i = Ints.fromBytes((byte) 1, (byte)2,(byte) 3,(byte) 4);
        //00000001 00000010 00000011 00000100(2) =  16909060
        System.out.println(i);
        byte[] bytes1 = Ints.toByteArray(32);


        //无符号支持

        //按无符号十进制解析字符串
        int i1 = UnsignedInts.parseUnsignedInt("12");
        //12
        System.out.println(i1);
        //数字按无符号十进制转为字符串
        String s = UnsignedInts.toString(-56);
        //4294967240 (二进制数为 11111111111111111111111111001000 )
        System.out.println(s);
    }
}
