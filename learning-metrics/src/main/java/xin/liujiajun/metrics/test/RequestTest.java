package xin.liujiajun.metrics.test;

import com.codahale.metrics.ConsoleReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xin.liujiajun.metrics.config.ThreadPoolUtil;
import xin.liujiajun.metrics.demo.MetricsCounterDemo;
import xin.liujiajun.metrics.demo.MetricsHistogramDemo;
import xin.liujiajun.metrics.demo.MetricsMeterDemo;
import xin.liujiajun.metrics.demo.MetricsTimerDemo;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2020-01-09 11:14
 **/
@Component
public class RequestTest {

    private static final Logger logger = LoggerFactory.getLogger(RequestTest.class);

    private Random random = new Random();

    @Autowired
    private MetricsMeterDemo meterDemo;

    @Autowired
    private MetricsCounterDemo counterDemo;

    @Autowired
    private MetricsTimerDemo timerDemo;
    @Autowired
    private MetricsHistogramDemo histogramDemo;

    @Autowired
    private ConsoleReporter consoleReporter;

    @PostConstruct
    public void init(){
        consoleReporter.start(10,TimeUnit.SECONDS);

        ThreadPoolUtil.poolExecutor.execute(()->{
            while (true) {
               meterDemo.hello();
            }
        });
        ThreadPoolUtil.poolExecutor.execute(()->{
            while (true) {
                counterDemo.hello();
            }
        });
        ThreadPoolUtil.poolExecutor.execute(()->{
            while (true) {
                timerDemo.hello();
            }
        });
        ThreadPoolUtil.poolExecutor.execute(()->{
            while (true) {
                histogramDemo.hello();
            }
        });
    }
}
