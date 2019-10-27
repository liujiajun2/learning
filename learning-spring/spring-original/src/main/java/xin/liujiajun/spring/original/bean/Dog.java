package xin.liujiajun.spring.original.bean;

import org.springframework.stereotype.Component;

/**
 * @author LiuJiaJun
 * @date 2019/6/13 0:11
 */
@Component
public class Dog implements Animal {
    @Override
    public void say() {
        System.out.println("wang wang wang ");
    }
}
