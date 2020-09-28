package xin.liujiajun.thread.framewark;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 多线程控制实用工具，简单称为倒计数器
 * 控制线程等待，直到倒计时结束，再开始执行。
 *
 * @author LiuJiaJun
 * @date 2019/1/11 22:04
 */
public class CountDownLatchDemo {

    static final CountDownLatch counter = new CountDownLatch(10);


    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(()->{
                try {
                    Thread.sleep(new Random().nextInt(10) * 1000);
                    System.out.println(Thread.currentThread().getName() + " :single complete");
                    counter.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        counter.await();
        System.out.println(Thread.currentThread().getName() +" :all complete!");
        exec.shutdown();

    }
}
