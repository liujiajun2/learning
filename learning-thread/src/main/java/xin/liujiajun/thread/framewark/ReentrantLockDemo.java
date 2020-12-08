package xin.liujiajun.thread.framewark;

import xin.liujiajun.thread.util.DateTimeUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiuJiaJun
 * @date 2019/5/14 10:43
 */
public class ReentrantLockDemo {
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public void await(){
        lock.lock();
        try {
            System.out.println("await: " + DateTimeUtil.now());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal(){
        try {
            lock.lock();
            System.out.println("signal: " + DateTimeUtil.now());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        new Thread(()->reentrantLockDemo.await()).start();
        TimeUnit.SECONDS.sleep(2);
        reentrantLockDemo.signal();
    }
}
