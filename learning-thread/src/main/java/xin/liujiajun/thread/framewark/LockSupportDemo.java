package xin.liujiajun.thread.framewark;

import java.util.concurrent.locks.LockSupport;

/**
 *  LockSupport 线程阻塞工具，park()可以阻塞当前线程，unpark()
 *  park() 和 unpark() 来取代suspend() 和 resume()
 *
 * @author LiuJiaJun
 * @date 2019/1/11 21:33
 */
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);

        t1.join();
        t2.join();
    }
}
