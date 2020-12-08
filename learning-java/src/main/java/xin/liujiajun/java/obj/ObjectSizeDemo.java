package xin.liujiajun.java.obj;

import org.apache.lucene.util.RamUsageEstimator;

/**
 * 关闭指针压缩：-XX:-UseCompressedOops
 *
 * @author liujiajun
 * @date 2020-11-24 08:39
 **/
public class ObjectSizeDemo {


    public static void main(String[] args) {
        Integer index = new Integer(120);
        int[] arrs = new int[1];
        Integer[] ints = new Integer[1];

        System.out.println(RamUsageEstimator.sizeOf(index));
        System.out.println(RamUsageEstimator.sizeOf(arrs));
        System.out.println(RamUsageEstimator.sizeOf(ints));

        //指针压缩：mark word 8 + class point 4 + array length 4 + int 4 * 2 = 24
        //指针未压缩：mark word 8 + class point 8 + array length 8 + int 4 * 2 = 32

        int[] arr2 = new int[2];
        //指针压缩：mark word 8 + class point 4 + array length 4 + int 4 * 3 = 28 再加填充4 = 32
        //指针未压缩：mark word 8 + class point 8 + array length 8 + int 4 * 3 = 36 再加填充4 = 40
        int[] arr3 = new int[3];
        System.out.println(RamUsageEstimator.sizeOf(arr2));
        System.out.println(RamUsageEstimator.sizeOf(arr3));

    }
}
