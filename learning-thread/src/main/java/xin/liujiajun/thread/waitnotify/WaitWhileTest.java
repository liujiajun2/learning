package xin.liujiajun.thread.waitnotify;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liujiajun
 * @date 2020-08-18 08:57
 **/
public class WaitWhileTest {


    private Queue<String> queue = new ArrayBlockingQueue<>(20);

    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    private void enq() {
        lock.lock();
        try {
            //错误用法，应该使用while
            if (queue.size() >= 20) {
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean insert = queue.add("ttt");
            System.out.println(Thread.currentThread().getName() + " insert result: " + insert);

            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    private void deq() {
        lock.lock();
        try {
            //错误用法，应该使用while
            if (queue.size() <= 0) {
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String poll = queue.poll();
            System.out.println(Thread.currentThread().getName() + "--- " + poll);
            full.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        WaitWhileTest waitWhileTest = new WaitWhileTest();

        for (int i = 0; i < 3; i++) {
            Pusher pusher = new Pusher(waitWhileTest);
            pusher.setName("push_" + (i + 1));
            pusher.start();
        }

        for (int i = 0; i < 2; i++) {
            Puller puller = new Puller(waitWhileTest);
            puller.setName("pull_" + (i + 1));
            puller.start();
        }
    }

    static class Pusher extends Thread {
        WaitWhileTest waitWhileTest;

        public Pusher(WaitWhileTest waitWhileTest) {
            this.waitWhileTest = waitWhileTest;
        }

        @Override
        public void run() {
            while (true) {
                waitWhileTest.enq();
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Puller extends Thread {
        WaitWhileTest waitWhileTest;

        public Puller(WaitWhileTest waitWhileTest) {
            this.waitWhileTest = waitWhileTest;
        }

        @Override
        public void run() {
            while (true) {
                waitWhileTest.deq();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
