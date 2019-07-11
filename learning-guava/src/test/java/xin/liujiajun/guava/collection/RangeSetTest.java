package xin.liujiajun.guava.collection;

import com.google.common.collect.*;
import org.junit.Test;

import java.time.Instant;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-28 16:35
 **/
public class RangeSetTest {

    @Test
    public void test(){

        TreeRangeMap<Long,String> rangeSet = TreeRangeMap.create();
        long l = System.currentTimeMillis();
        for (int i = 0; i < 100_000_0; i++) {
            Long start = Instant.now().getEpochSecond();
            Long end = Instant.now().getEpochSecond() + i;
            rangeSet.put(Range.closed(start,end),String.valueOf(i));
        }
        Long start = Instant.now().getEpochSecond();
        Long end = Instant.now().getEpochSecond() + 3600 * 2;
        RangeMap<Long, String> longStringRangeMap = rangeSet.subRangeMap(Range.closed(start, end));

        System.out.println(longStringRangeMap);

        System.out.println(System.currentTimeMillis() - l);


    }

    @Test
    public void testRange(){
        long epochSecond = Instant.now().getEpochSecond();
        long duration = 100L;
        Range<Long> closed = Range.closed(epochSecond, epochSecond + 10000);
        Range<Long> closed1 = null;
        for (int i = 0; i < 1000; i++) {
            int j = 1;
            if (i % 2 == 0) {
                j = -1;
            }
            long start = epochSecond + j * i;
            long end = epochSecond + duration  + j * i;

            closed1 = Range.closed(start, end);
            boolean connected = closed.isConnected(closed1);
            System.out.println("is connected " + connected);
            if (!connected){
                System.out.println(epochSecond + ": " + (epochSecond + 10000));
                System.out.println(start + ": " + end);
            }
        }
    }
    

}
