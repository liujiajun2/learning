package xin.liujiajun.guava.collection.newconllection;

import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeMap;

/**
 * @author liujiajun
 * @description RangeMap demo
 * @create 2019-03-12 14:39
 **/
public class RangeMapDemo {
    public static void main(String[] args) {
        TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1,10),"foo");
        // [[1..10]=foo]
        System.out.println(rangeMap);
        rangeMap.put(Range.open(3,6),"bar");
        // [[1..3]=foo, (3..6)=bar, [6..10]=foo]
        System.out.println(rangeMap);

        rangeMap.put(Range.open(10,20),"foo");
        // [[1..3]=foo, (3..6)=bar, [6..10]=foo, (10..20)=foo]
        System.out.println(rangeMap);

        rangeMap.remove(Range.closed(5,11));
        // [[1..3]=foo, (3..5)=bar, (11..20)=foo]
        System.out.println(rangeMap);

    }
}
