package xin.liujiajun.java.batchmessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-12-23 14:52
 **/
public class TestPutMessageService extends BatchMessageService<String> {

    private int total = 0;


    @Override
    protected void doBatchMessage(List<String> messages) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println("time " + now +",commit size is: " + messages.size());
        total += messages.size();
        System.out.println("total : " + total);

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
