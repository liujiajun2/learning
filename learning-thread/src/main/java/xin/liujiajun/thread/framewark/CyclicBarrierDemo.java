package xin.liujiajun.thread.framewark;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CyclicBarrier 是一种并发控制使用工具，可以实现线程间的计数等待。
 * 可以理解为循环栅栏。可以设置计数器，凑齐数据器数量，计数器会归零，并且执行相应的线程操作。
 *
 *
 * @author LiuJiaJun
 * @date 2019/1/11 21:42
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclic;
        Soldier(CyclicBarrier cyclic,String soldierName){
            this.cyclic = cyclic;
            this.soldier = soldierName;
        }

        public void run() {
            try {
                cyclic.await();
                doWork();
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        private void doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ":任务完成");
        }
    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;
        public BarrierRun(boolean flag,int N){
            this.flag = flag;
            this.N = N;
        }

        public void run() {
            if(flag){
                System.out.println("司令：【士兵" + N + "个，任务完成】");
            }else{
                System.out.println("司令：【士兵" + N + "个，集合完毕】");
                flag = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        final int n = 10;
//        Thread[] threads = new Thread[n];
//        boolean flag = false;
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, new BarrierRun(flag, n));
//        System.out.println("集合队伍");
//
//        for (int i = 0; i < n; i++) {
//            System.out.println("士兵" + i+ "报到");
//            threads[i] = new Thread(new Soldier(cyclicBarrier,"士兵" + i));
//            threads[i].start();
//        }

        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println(Thread.currentThread().getName() + " : all completed!!!");
        });
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service.submit(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(3000));
                    System.out.println(Thread.currentThread().getName() + " : complete");
                    barrier.await();
                }catch (Exception ignore) {

                }
            });
        }


    }
}
