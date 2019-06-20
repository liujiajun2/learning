package xin.liujiajun.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import xin.liujiajun.spring.bean.Animal;
import xin.liujiajun.spring.bean.Cat;
import xin.liujiajun.spring.bean.HelloBean;

/**
 * @author LiuJiaJun
 * @date 2019/6/13 0:01
 */
@Configuration
public class Config {

    @Bean(value = {"hello","world"},destroyMethod = "")
    public HelloBean createHello(){
        return new HelloBean();
    }


    @Bean(value = {"hello","test"})

    public Object hello(){
        return new Object();
    }

}
