package xin.liujiajun.java.thread;

class SubThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <=100 ; i++) {
            if( i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+ i);
            }
        }
    }
}
class SubThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <=100 ; i++) {
            if( i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+":"+ i);
            }
        }
    }
}
public class Demo {
    public static void main(String[] args) {
        SubThread1 s1 = new SubThread1();
        SubThread2 s2 = new SubThread2();
        s1.start();
        s2.start();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <50 ; i++) {
                    System.out.println(i);
                }
            }
        }.start();
    }
}
