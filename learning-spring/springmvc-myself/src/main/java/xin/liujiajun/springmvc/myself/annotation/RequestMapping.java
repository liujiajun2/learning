package xin.liujiajun.springmvc.myself.annotation;

import java.lang.annotation.*;

/**
 * @author LiuJiaJun
 * @date 2019/6/29 11:25
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String value() default "";
}
