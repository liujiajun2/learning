package xin.liujiajun.thread.waitnotify;

/**
 * @author LiuJiaJun
 * @date 2019/5/14 9:38
 */
public class CrossPrint {

    private volatile boolean exist = false;

    public synchronized void backupA() {
        try {
            while (exist) {
                wait();
            }
            System.out.println("★★★★★");
            exist = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void backupB(){
        try{
            while (!exist){
                wait();
            }
            System.out.println("☆☆☆☆☆");
            exist = false;
            notifyAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CrossPrint crossPrint = new CrossPrint();
        for (int i = 0; i < 20; i++) {
            new Thread(()-> crossPrint.backupA()).start();
            new Thread(()-> crossPrint.backupB()).start();
        }
        new ThreadLocal<>();
    }
}
