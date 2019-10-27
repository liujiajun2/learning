package xin.liujiajun.spring.original.bean;

import org.springframework.stereotype.Component;

/**
 * @author LiuJiaJun
 * @date 2019/6/13 0:12
 */
@Component
public class Cat implements Animal {
    @Override
    public void say() {
        System.out.println("miao miao miao");
    }
}
