package xin.liujiajun.thread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author LiuJiaJun
 * @date 2019/5/14 12:13
 */
public class TimerDemo {

    static int i = 0;
    public static class MyTask extends TimerTask{

        @Override
        public void run() {
            System.out.println("执行中    " + i);
        }
    }

    public static void main(String[] args) {
        while (i < Integer.MAX_VALUE){
            try {
                i++;
                Timer timer = new Timer();
                MyTask myTask = new MyTask();
                timer.schedule(myTask,new Date());
                timer.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
