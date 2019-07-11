package xin.liujiajun.guava.concurrency;

import com.google.common.util.concurrent.*;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liujiajun
 * @description Service demo
 * @create 2019-03-15 10:48
 **/
public class ServiceDemo {

    public static void main(String[] args) {


        AbstractIdleService service1 = new AbstractIdleService() {

            @Override
            protected void startUp() throws Exception {
                System.out.println("开始");
            }

            @Override
            protected void shutDown() throws Exception {
                System.out.println("结束");
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        service1.addListener(new Service.Listener() {
            @Override
            public void starting() {
                super.starting();
                System.out.println("starting");
            }

            @Override
            public void running() {
                super.running();
                System.out.println("running");

            }

            @Override
            public void stopping(Service.State from) {
                super.stopping(from);
                System.out.println("stopping");

            }

            @Override
            public void terminated(Service.State from) {
                super.terminated(from);
                System.out.println("terminated");

            }

            @Override
            public void failed(Service.State from, Throwable failure) {
                super.failed(from, failure);
                System.out.println("failed");

            }
        }, executorService);
        service1.startAsync().awaitRunning();

        Service.State state = service1.state();
        System.out.println(state);
        service1.stopAsync();
        executorService.shutdown();


        AbstractExecutionThreadService service2 = new AbstractExecutionThreadService() {
            @Override
            protected void run() throws Exception {
                System.out.println("run ");
            }
        };

        AbstractScheduledService service3 = new AbstractScheduledService() {

            @Override
            protected void runOneIteration() throws Exception {

            }

            @Override
            protected Scheduler scheduler() {
                return null;
            }
        };


        AbstractService service4 = new AbstractService() {

            @Override
            protected void doStart() {
                System.out.println("start");
            }

            @Override
            protected void doStop() {
                System.out.println("stop");
            }
        };

        ServiceManager serviceManager = new ServiceManager(Arrays.asList(service2));

        new Thread(()-> System.out.println("fff")).start();
    }
}
