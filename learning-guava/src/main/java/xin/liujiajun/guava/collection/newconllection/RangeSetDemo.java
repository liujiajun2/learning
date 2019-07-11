package xin.liujiajun.guava.collection.newconllection;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

import java.util.Set;

/**
 * @author liujiajun
 * @description RangeSet demo
 * @create 2019-03-12 14:23
 **/
public class RangeSetDemo {

    public static void main(String[] args) {
        TreeRangeSet<Integer> rangeSet = TreeRangeSet.create();
        //添加区间 [1,10]
        rangeSet.add(Range.closed(1,10));
        //[[1..10]]
        System.out.println(rangeSet);
        //添加区间 [11,15)
        rangeSet.add(Range.closedOpen(11,15));
        //[[1..10], [11..15)]
        System.out.println(rangeSet);
        //添加区间 [15,20)
        rangeSet.add(Range.closedOpen(15,20));
        //[[1..10], [11..20)]
        System.out.println(rangeSet);
        //添加空区间 （0,0]
        rangeSet.add(Range.openClosed(0,0));
        //[[1..10], [11..20)]
        System.out.println(rangeSet);
        //移除区间 (5,10)
        rangeSet.remove(Range.open(5,10));
        //[[1..5], [10..10], [11..20)]
        System.out.println(rangeSet);

        //返回RangeSet的补集视图
        RangeSet<Integer> complement = rangeSet.complement();
        //[(-∞..1), (5..10), (10..11), [20..+∞)]
        System.out.println(complement);
        Set<Range<Integer>> ranges = rangeSet.asRanges();
        System.out.println(ranges);
        //是否有任何区间包含给定元素
        boolean contains = rangeSet.contains(6);
        //false
        System.out.println(contains);
        //返回包括RangeSet中所有区间的最小区间
        Range<Integer> span = rangeSet.span();
        //[1..20)
        System.out.println(span);
    }
}
