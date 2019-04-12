package xin.liujiajun.java.thread;

class PrintNum implements Runnable{
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            if(i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}
public class TestRunnable {
    public static void main(String[] args) {
        PrintNum p = new PrintNum();
        Thread t = new Thread(p);
        t.start();

        Thread t2 = new Thread(p);
        t2.start();
    }
}
