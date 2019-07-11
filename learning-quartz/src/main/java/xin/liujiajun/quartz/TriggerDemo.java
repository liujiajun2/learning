package xin.liujiajun.quartz;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.spi.MutableTrigger;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-26 15:23
 **/
public class TriggerDemo {

    public static void main(String[] args) {
        MutableTrigger build = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(2).build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(20)
                                .repeatForever()
                )
                .build();
    }
}
