package xin.liujiajun.metrics.demo;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2020-01-09 11:07
 **/
@Component
public class MetricsMeterDemo {

    @Autowired
    private Meter requestMeter;

    private Random random = new Random();

    public void hello() {

        requestMeter.mark();

        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
