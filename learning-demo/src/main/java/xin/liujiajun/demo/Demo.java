package xin.liujiajun.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-11-26 11:26
 **/
public class Demo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("begin========================");
        int pid = getPid();
        System.out.println("pid : " + pid);
        System.out.println("cost time " + (System.currentTimeMillis() - start) + "ms");
    }
    public static int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }
}
