package xin.liujiajun.metrics.demo;

import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2020-01-10 16:58
 **/
@Component
public class MetricsTimerDemo {

    @Autowired
    private Timer timer;
    private Random random = new Random();

    public void hello() {
        Timer.Context context = timer.time();
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
        } catch (Exception e) {

        } finally {
            context.stop();
        }
    }
}
