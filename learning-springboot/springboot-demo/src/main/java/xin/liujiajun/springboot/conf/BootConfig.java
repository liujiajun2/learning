package xin.liujiajun.springboot.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author LiuJiaJun
 * @date 2019/5/31 10:09
 */
@Configuration
public class BootConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("Schedule-");
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }
}
