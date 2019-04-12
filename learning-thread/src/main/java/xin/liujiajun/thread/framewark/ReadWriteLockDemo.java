package xin.liujiajun.thread.framewark;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author LiuJiaJun
 * @date 2019/1/11 22:11
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;
    private static CountDownLatch count = new CountDownLatch(20);


    public Object handleRead(Lock lock) throws Exception{
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }
    }
    public void handleWrite(Lock lock,int index) throws Exception{
        try{
            lock.lock();
            Thread.sleep(1000);
            value = index;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception{
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            public void run() {
                try {
                    demo.handleRead(readLock);
//                    demo.handleRead(lock);
                    count.countDown();
                }catch (Exception e){

                }
            }
        };
        Runnable writeRunnable =new Runnable() {
            public void run() {
                try {
                    demo.handleWrite(writeLock,new Random().nextInt());
//                    demo.handleWrite(lock,new Random().nextInt());
                    count.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        long start = System.currentTimeMillis() / 1000;
        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
        count.await();
        long end = System.currentTimeMillis() / 1000;
        System.out.println("using times :" + (end - start) + "s");
    }
}
