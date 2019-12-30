package xin.liujiajun.java.test;

/**
 * @author liujiajun
 * @date 2019-12-11 10:08
 **/
public class Test {

    public static void main(String[] args) {
        PrintThread printThread = new PrintThread();
        new Thread(printThread).start();
        new Thread(printThread).start();
        new Thread(printThread).start();
        new Thread(printThread).start();
    }
    private static class PrintThread implements Runnable{

        @Override
        public void run() {
            int i = 0;
            while (i < 10) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i++);
            }
        }
    }
}
