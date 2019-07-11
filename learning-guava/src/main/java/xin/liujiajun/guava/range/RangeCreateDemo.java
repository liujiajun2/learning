package xin.liujiajun.guava.range;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

/**
 * @author LiuJiaJun
 * @date 2019/3/14 22:27
 */
public class RangeCreateDemo {

    public static void main(String[] args) {

        //构造区间
        //(1..2)
        Range<Integer> open = Range.open(1, 2);
        //[1..2]
        Range<Integer> closed = Range.closed(1, 2);
        //[1,2)
        Range<Integer> integerRange = Range.closedOpen(1, 2);
        //(1,2]
        Range<Integer> integerRange1 = Range.openClosed(1, 2);
        // (1..+∞)
        Range<Integer> integerRange2 = Range.greaterThan(1);
        //[1..+∞)
        Range<Integer> integerRange3 = Range.atLeast(1);
        //(-∞..1)
        Range<Integer> integerRange4 = Range.lessThan(1);
        //(-∞..1]
        Range<Integer> integerRange5 = Range.atMost(1);
        //(-∞..+∞)
        Range<Comparable<?>> all = Range.all();
        // 有界区间 [1,2]
        Range<Integer> range = Range.range(1, BoundType.CLOSED, 2, BoundType.CLOSED);
        // 无上界区间 [1..+∞)
        Range<Integer> integerRange6 = Range.downTo(1, BoundType.CLOSED);
        // 无下界区间 (-∞..1]
        Range<Integer> integerRange7 = Range.upTo(1, BoundType.CLOSED);



    }
}
