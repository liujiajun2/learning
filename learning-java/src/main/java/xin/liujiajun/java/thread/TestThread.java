package xin.liujiajun.java.thread;

/**
 *创建多线程的第一种方式，继承Thread类
 * Thread 常用的方法
 * 1.start();启动并执行相应的run()方法
 * 2.run()子线程要执行的代码
 * 3.currentThread（）静态的，调取当前的线程
 * 4.yield 调用此方法的线程释放当前CPU的执行权
 * 5.join()：在A线程中调用B线程的join方法，表示当执行到此方法，A线程停止，直至B线程执行完毕，A再接着执行
 * 6.isAlive();判断当前线程是否存活
 * 7.sleep(long 1)：显式的让当前线程睡眠1毫秒
 * 8.线程通信：wait(),notify(),notifyAll();
 *
 * 设置线程优先级：
 * setPriority
 */
class SUbThread extends Thread{
    @Override
    public void run() {
        for (int i =1; i <100 ; i++) {
            System.out.println(Thread.currentThread().getName()+i);
        }
//        super.run();
    }
}
public class TestThread {
    public static void main(String[] args) {
        SUbThread sUbThread = new SUbThread();
        sUbThread.setPriority(Thread.MAX_PRIORITY);
        sUbThread.start();
        sUbThread.setName("子线程");
        Thread.currentThread().setName("============主线程");
        for (int i =1; i <100 ; i++) {
            System.out.println(Thread.currentThread().getName()+Thread.currentThread().getPriority()+i);
//            if( i %10 == 0){
//                Thread.currentThread().yield();
//            }
//            if(i == 20){
//                try {
//                    sUbThread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        System.out.println(sUbThread.isAlive());
        System.out.println(Thread.currentThread().isAlive());
    }
}
