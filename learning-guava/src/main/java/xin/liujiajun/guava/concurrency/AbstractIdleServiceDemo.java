package xin.liujiajun.guava.concurrency;

import com.google.common.util.concurrent.AbstractIdleService;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-15 14:32
 **/
public class AbstractIdleServiceDemo extends AbstractIdleService {


    @Override
    protected void startUp() throws Exception {
        System.out.println("启动");
    }

    @Override
    protected void shutDown() throws Exception {
        System.out.println("关闭");
    }



    public static void main(String[] args) {
        AbstractIdleServiceDemo demo = new AbstractIdleServiceDemo();
        demo.startAsync();
    }
}
