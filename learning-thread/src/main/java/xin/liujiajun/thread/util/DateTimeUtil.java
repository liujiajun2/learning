package xin.liujiajun.thread.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author LiuJiaJun
 * @date 2019/5/14 10:48
 */
public class DateTimeUtil {

    public static String now(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss n");
        return now.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(now());
    }
}
