package xin.liujiajun.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import xin.liujiajun.springboot.autoconfig.model.Model;
import xin.liujiajun.springboot.filter.MyFilter;

import javax.annotation.PostConstruct;

/**
 * @author LiuJiaJun
 * @date 2019/5/30 13:04
 */
//使之能找到Listener
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {
        "xin.liujiajun.springboot.conf",
        "xin.liujiajun.springboot.controller",
        "xin.liujiajun.springboot.handler",
        "xin.liujiajun.springboot.model",
        "xin.liujiajun.springboot.service",
        "xin.liujiajun.springboot.event",
        "xin.liujiajun.springboot.schedule",
})
@EnableScheduling
public class BootApplication {

    @Autowired
    private Model model;


    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class,args);

    }

    @PostConstruct
    public void init(){
        System.out.println("==========测试输出开始============");
        System.out.println(model);
        System.out.println("==========测试输出结束============");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/");
        registrationBean.addInitParameter("paramName","value");
        registrationBean.setName("myFilter");
        return registrationBean;
    }




}
