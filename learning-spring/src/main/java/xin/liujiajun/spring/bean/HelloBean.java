package xin.liujiajun.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author LiuJiaJun
 * @date 2019/6/13 0:04
 */
public class HelloBean {

    @Autowired
    @Qualifier(value = "dog")
    private Animal animal;

    public void say() {
        animal.say();
        System.out.println("hello world");
    }

    //如果有shundown 和 close bean销毁的时候会执行close，只有shutdown会执行shutdown，@Bean(destroyMethod = "") 可以取消这种默认的行为
    public void shutdown(){
        System.out.println("shutdown");
    }

    public void close(){
        System.out.println("close");
    }
}
