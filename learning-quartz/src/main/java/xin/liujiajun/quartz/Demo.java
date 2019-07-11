package xin.liujiajun.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author liujiajun
 * @create 2019-04-23 14:44
 **/
public class Demo {

    public static void main(String[] args) throws SchedulerException {
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

        Scheduler scheduler = stdSchedulerFactory.getScheduler();


        JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("myJob", "group1").build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .withRepeatCount(2)
                )
                .build();

        scheduler.scheduleJob(job,trigger);
        scheduler.start();

    }
}
