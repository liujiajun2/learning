package xin.liujiajun.java.thread;

/**
 * @author liujiajun
 * @date 2020-11-12 10:46
 **/
public class NoVisibility extends Thread {

    boolean keepRunning = true;

    public static void main(String[] args) throws InterruptedException {
        NoVisibility noVisibility = new NoVisibility();
        noVisibility.start();
        System.out.println("start " + noVisibility.keepRunning);
        Thread.sleep(1000);
        noVisibility.keepRunning = false;
        noVisibility.setKeepRunning(false);

        System.out.println("end " + noVisibility.keepRunning);
    }

    @Override
    public void run() {
        int x = 1;
        while (isKeepRunning()) {
            System.out.println("增加打印，程序会退出");
            x++;
        }
        System.out.println("x=" +x);
    }

    public boolean isKeepRunning() {
        return keepRunning;
    }

    public NoVisibility setKeepRunning(boolean keepRunning) {
        this.keepRunning = keepRunning;
        return this;
    }
}
