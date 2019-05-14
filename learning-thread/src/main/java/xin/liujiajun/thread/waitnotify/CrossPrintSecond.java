package xin.liujiajun.thread.waitnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiuJiaJun
 * @date 2019/5/14 11:05
 */
public class CrossPrintSecond {

    private static volatile int newPrint = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            try {
                lock.lock();
                while (newPrint != 1) {
                    conditionA.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadA " + (i + 1));
                }
                newPrint = 2;
                conditionB.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                lock.lock();
                while (newPrint != 2) {
                    conditionB.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadB " + (i + 1));
                }
                newPrint = 3;
                conditionC.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                lock.lock();
                while (newPrint != 3) {
                    conditionC.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadC " + (i + 1));
                }
                newPrint = 1;
                conditionA.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        for (int i = 0; i < 5; i++) {
            new Thread(threadA).start();
            new Thread(threadB).start();
            new Thread(threadC).start();
        }
    }
}
