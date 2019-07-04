package xin.liujiajun.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xin.liujiajun.springboot.autoconfig.model.Model;

import javax.annotation.PostConstruct;

/**
 * @author LiuJiaJun
 * @date 2019/5/30 13:04
 */
@SpringBootApplication(scanBasePackages = {
        "xin.liujiajun.springboot.conf",
        "xin.liujiajun.springboot.controller",
        "xin.liujiajun.springboot.handler",
        "xin.liujiajun.springboot.model",
        "xin.liujiajun.springboot.service",
})
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


}
