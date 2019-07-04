package xin.liujiajun.springboot.autoconfig.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import xin.liujiajun.springboot.autoconfig.model.Model;

/**
 * @author liujiajun
 * @create 2019-07-03 19:41
 **/
@Configuration
@AutoConfigureOrder(value = 9999)
public class Config {


    @Bean
//    @ConditionalOnClass()
//    @ConditionalOnMissingClass()
//
//    @ConditionalOnBean
//    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "xin.liujiajun.test")
    public Model createModel(){
        return new Model(1,"zhangsan");
    }


    public Object createObject(){
        return new Object();
    }


}
