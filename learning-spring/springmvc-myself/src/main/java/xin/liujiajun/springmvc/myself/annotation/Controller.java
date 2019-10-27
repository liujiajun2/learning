package xin.liujiajun.springmvc.myself.annotation;

import java.lang.annotation.*;

/**
 * @author LiuJiaJun
 * @date 2019/6/29 11:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {

    String value() default "";
}
