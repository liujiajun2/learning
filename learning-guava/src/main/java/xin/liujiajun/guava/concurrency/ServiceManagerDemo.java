package xin.liujiajun.guava.concurrency;

import com.google.common.util.concurrent.ServiceManager;

import java.util.Arrays;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-15 14:50
 **/
public class ServiceManagerDemo {

    public static void main(String[] args) {
        AbstractScheduledServiceDemo de = new AbstractScheduledServiceDemo();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(de));
        serviceManager.startAsync();
    }
}
