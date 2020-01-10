package xin.liujiajun.metrics.demo;

import com.codahale.metrics.Histogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2020-01-10 17:02
 **/
@Component
public class MetricsHistogramDemo {


    @Autowired
    private Histogram histogram;

    private Random random = new Random();

    public void hello() {

        histogram.update(random.nextInt());

        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
