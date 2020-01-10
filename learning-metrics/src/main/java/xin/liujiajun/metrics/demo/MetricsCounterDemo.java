package xin.liujiajun.metrics.demo;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2020-01-10 17:08
 **/
@Component
public class MetricsCounterDemo {

    @Autowired
    private Counter counter;

    private Random random = new Random();

    public void hello() {

        counter.inc();

        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
