package xin.liujiajun.java.common;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {

    @Test
    public void test(){
        Date d1 = new Date();
        System.out.println(d1.toString());
        System.out.println(d1.getTime());//long型的值

        //格式化1
        SimpleDateFormat format = new SimpleDateFormat();
        String date = format.format(new Date());
        System.out.println(date);

        //2格式化2
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
        String dt = sd.format(new Date());
        System.out.println(dt);

        //解析文本到日期
        try {
            Date dd = sd.parse("2018-05-17 19:52:51");
            System.out.println(dd);
            dd = format.parse("18-5-17 下午7:53");
            System.out.println(dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2(){
        //日历
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);

        calendar.add(Calendar.DAY_OF_MONTH,-2);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);

        calendar.set(Calendar.DAY_OF_MONTH,23);
        Date d = calendar.getTime();
        System.out.println(d);
    }
}
