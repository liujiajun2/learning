package xin.liujiajun.netty.traffic;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @create 2019-07-25 19:19
 **/
public class AppChannleInializer extends ChannelInitializer {

    private static final EventExecutorGroup executor = new DefaultEventExecutorGroup(Runtime.getRuntime().availableProcessors() * 2);
    private static final GlobalTrafficShapingHandler trafficHandler = new GlobalTrafficShapingHandler(executor,3000000,3000000);

    static {

        new Thread(()->{
           while (true) {
               TrafficCounter trafficCounter = trafficHandler.trafficCounter();
               try {
                   TimeUnit.SECONDS.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               long totalRead = trafficCounter.cumulativeReadBytes();
               long totalWrite = trafficCounter.cumulativeWrittenBytes();

               System.out.println("total read " + (totalRead >> 10) + "KB");
               System.out.println("total write " + (totalWrite >> 10) + "KB");

               System.out.println("流量控制： " + System.lineSeparator() + trafficCounter);
           }
        }).start();
    }
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(trafficHandler);
        channel.pipeline().addLast(new HelloHandler());
    }
}
