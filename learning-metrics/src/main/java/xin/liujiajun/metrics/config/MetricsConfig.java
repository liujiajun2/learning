package xin.liujiajun.metrics.config;

import com.codahale.metrics.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2020-01-09 11:07
 **/
@Configuration
public class MetricsConfig {

    @Bean
    public MetricRegistry metricRegistry(){
        return new MetricRegistry();
    }

    /**
     * TPS计数器，并不准确，meters工具会帮助我们统计系统中某一个事件的速率。
     * 每秒请求数，每秒查询数，这个指标反映系统当前的处理能力。
     * @return
     */
    @Bean
    public Meter requestMeter(MetricRegistry metricRegistry){
        return metricRegistry.meter("request");
    }

    /**
     * 直方图数据。可以统计某个方法的网络流量，
     * 定义了最大值，最小值，和平均值。可以绘制自定义图形。
     *
     * @param metrics
     * @return
     */
    @Bean
    public Histogram responseSizes(MetricRegistry metrics){
        return metrics.histogram("response");
    }

    /**
     * 计数器，用来统计队列汇总jdb的总数
     *
     * @param metrics
     * @return
     */
    @Bean
    public Counter createCounter(MetricRegistry metrics){
        return metrics.counter("requestCount");
    }

    @Bean
    public Timer createTimer(MetricRegistry metrics){
        return metrics.timer("executeTime");
    }




    @Bean
    public ConsoleReporter consoleReporter(MetricRegistry registry) {
        return ConsoleReporter
                .forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MICROSECONDS)
                .build();
    }

    @Bean
    public Slf4jReporter slf4jReporter(MetricRegistry registry) {
        return Slf4jReporter
                .forRegistry(registry)
                .outputTo(LoggerFactory.getLogger("xin.liujiajun.log"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MICROSECONDS)
                .build();
    }


}
