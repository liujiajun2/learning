package xin.liujiajun.springboot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liujiajun
 * @date 2019-08-13 13:44
 **/
@Component
public class Demo {


    @Scheduled(cron = "0 0 14 * * ?")
    public void test(){
        System.out.println("0 0 14 * * ?  ==>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")));
    }

    @Scheduled(cron = "0 0 14 * * *")
    public void test02(){
        System.out.println("0 0 14 * * *  ==>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")));
    }
}
