package xin.liujiajun.java.thread;
/**
 * 线程通信
 * wait,notify notifyAll
 * 这三个方法都必须在同步代码或者同步代码块中
 */

class PrintNumber implements Runnable{
    int num =1;
    public void run() {
        while (true){
            synchronized(this){
                notify();
                if (num <= 100){
                    try {
                        Thread.currentThread().sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+num++);
                }else{
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
public class TestCommunication {
    public static void main(String[] args) {
        PrintNumber p = new PrintNumber();
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(p);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();
    }
}
