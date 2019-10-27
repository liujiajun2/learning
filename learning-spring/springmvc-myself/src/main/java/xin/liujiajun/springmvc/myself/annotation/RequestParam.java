package xin.liujiajun.springmvc.myself.annotation;

import java.lang.annotation.*;

/**
 * @author LiuJiaJun
 * @date 2019/6/29 11:26
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
}
