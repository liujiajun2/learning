package xin.liujiajun.guava.concurrency;

import com.google.common.util.concurrent.AbstractScheduledService;

import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-15 14:44
 **/
public class AbstractScheduledServiceDemo extends AbstractScheduledService {
    @Override
    protected void runOneIteration() throws Exception {
        System.out.println("schedule Task");
    }

    @Override
    protected Scheduler scheduler() {
        return AbstractScheduledService.Scheduler.newFixedRateSchedule(1,2, TimeUnit.SECONDS);
    }


    public static void main(String[] args) throws InterruptedException {
        AbstractScheduledServiceDemo demo = new AbstractScheduledServiceDemo();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.startAsync();
            }
        });
        thread.setDaemon(false);
        thread.start();
        //demo.startAsync();
        //TimeUnit.SECONDS.sleep(10);

    }
}
