package xin.liujiajun.guava.range;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

/**
 * @author liujiajun
 * @description RangeCalculate demo
 * @create 2019-03-15 10:01
 **/
public class RangeCalculateDemo {

    public static void main(String[] args) {

        boolean contains = Range.closed(1, 3).contains(4);
        //false
        System.out.println(contains);
        boolean contains1 = Range.closed(1, 4).containsAll(Ints.asList(2, 3, 4));
        //true
        System.out.println(contains1);


        boolean empty = Range.closed(4, 4 ).isEmpty();
        //false
        System.out.println(empty);
        Integer integer = Range.closed(3, 10).lowerEndpoint();
        // 3 返回区间的端点
        System.out.println(integer);
        BoundType boundType = Range.open(3, 10).lowerBoundType();
        //OPEN
        System.out.println(boundType);

        //包含运算
        boolean encloses = Range.closed(3, 6).encloses(Range.closed(4, 6));
        //true
        System.out.println(encloses);
        boolean encloses1 = Range.closed(3, 5).encloses(Range.closed(4, 6));
        //false
        System.out.println(encloses1);


        //相连
        boolean connected = Range.closed(3, 5).isConnected(Range.open(5, 10));
        //true
        System.out.println(connected);
        boolean connected1 = Range.closed(1, 5).isConnected(Range.closed(6, 10));
        //false
        System.out.println(connected1);

        //交集
        Range<Integer> intersection = Range.closed(3, 5).intersection(Range.open(5, 10));
        //(5..5]
        System.out.println(intersection);
        //java.lang.IllegalArgumentException: Invalid range: [6..5]
       // Range<Integer> intersection1 = Range.closed(1, 5).intersection(Range.closed(6, 10));
       // System.out.println(intersection1);

        //跨区域
        Range<Integer> span = Range.closed(3, 5).span(Range.open(5, 10));
        //[3..10)
        System.out.println(span);
        Range<Integer> span1 = Range.closed(1, 5).span(Range.closed(3, 9));
        //[1..9]
        System.out.println(span1);
    }
}
