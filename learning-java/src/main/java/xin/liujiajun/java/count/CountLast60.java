package xin.liujiajun.java.count;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 统计1分钟内的请求数
 *
 * @author liujiajun
 * @date 2019-08-19 14:11
 **/
public class CountLast60 {

    private AtomicLong count = new AtomicLong(0);

    private int precision = 60;

    private volatile int currentCount = 0;

    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(precision);

    private volatile boolean stopped = false;

    private volatile boolean init = false;

    private volatile int maxCount = 0;

    private ScheduledExecutorService service;

    public synchronized void increase() {
        currentCount++;
        if (!init) {
            init = true;
            service = Executors.newScheduledThreadPool(1);
            service.scheduleAtFixedRate(this::add, 0, 1, TimeUnit.SECONDS);
        }
    }

    public long getCount() {
        return count.get();
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void stop() {
        this.stopped = true;
    }

    public synchronized void add() {
        if (stopped) {
            if (service.isShutdown()) {
                service.shutdown();
            }
            return;
        }
        System.out.println("currentCount " + currentCount);
        if (queue.size() == precision) {
            Integer oldData = queue.poll();
            System.out.println("移除的值为 ：" + oldData);
            count.getAndAdd(-oldData);
            //移除的是最大值
            if (maxCount == oldData) {
                Iterator<Integer> iterator = queue.iterator();
                int tmpMaxCount = 0;
                while (iterator.hasNext()) {
                    Integer tmp = iterator.next();
                    if (tmp > tmpMaxCount) {
                        tmpMaxCount = tmp;
                    }
                }
                //如果最大值产生了变化，那么需要重新生成一个
                if (maxCount != tmpMaxCount) {
                    System.out.println("最大值变更为：" + tmpMaxCount);
                    maxCount = tmpMaxCount;
                }
            }
        }
        if (currentCount > maxCount) {
            maxCount = currentCount;
        }
        queue.offer(currentCount);
        count.getAndAdd(currentCount);
        currentCount = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        CountLast60 countLast60 = new CountLast60();


        for (int i = 0; i < 99; i++) {
            int len = new Random().nextInt(100);
            for (int j = 0; j < len; j++) {
                countLast60.increase();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println("第 " + (i + 1) + " 次执行，数量为：" + countLast60.getCount());
        }


    }
}
