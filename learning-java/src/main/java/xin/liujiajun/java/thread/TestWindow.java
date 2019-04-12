package xin.liujiajun.java.thread;
//模拟火车站售票，开启三个窗口售票

/**
 * 存在线程安全问题
 * 实现线程安全，采用线程同步机制
 * 方式一：同步代码块
 *   synchronized(同步监视器){
 *       //需要被同步的代码块（操作共享数据的代码）
 *   }
 *   1.共享数据，多个线程共同操作的同一个数据
 *   2.同步监视器，类的对象充当。哪个线程获得此监视器，谁就执行大括号里面被同步的代码。俗称：锁
 *   3.所有的线程共用同一把锁
 * 方式二：同步方法
 *  将操作共享数据的方法声明为synchronized，即此方法为同步方法，能够保证其中一个线程执行此方法时，其他线程在外等待直至该线程完成
 *  >同步方法的锁：this
 *
 *  线程同步弊端，同一时间只能一个线程访问共享数据，效率变低了
 */
class Window1 implements Runnable{
    int ticket = 100;
//    Object object = new Object();
    public void run() {
        while (true){
            synchronized(this){//this为当前对象
                if( ticket > 0){
                    try {
                        Thread.currentThread().sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":" + ticket--);
                }else{
                    break;
                }
            }
        }
    }
}
public class TestWindow {
    public static void main(String[] args) {
        Window1 w = new Window1();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
