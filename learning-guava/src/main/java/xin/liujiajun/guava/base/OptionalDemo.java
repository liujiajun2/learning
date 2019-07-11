package xin.liujiajun.guava.base;

import com.google.common.base.Optional;


/**
 * Using and avoiding null: null can be ambiguous, can cause confusing errors, and is sometimes just plain unpleasant.
 * Many Guava utilities reject and fail fast on nulls, rather than accepting them blindly
 */

/**
 * @author liujiajun
 * @description Optional Demo
 * @create 2019-03-11 15:20
 **/

public class OptionalDemo {
    public static void main(String[] args) {
        //Optional.of(T) 创建制定引用的Optional实例，如果引用为null则快速失败
        Optional<Integer> possible = Optional.of(2);
        // 创建引用缺失的Optional实例
        Optional<Object> absent = Optional.absent();
        // 创建制定引用的Optional实例，若引用为null则表示缺失
        Optional<Object> nullable = Optional.fromNullable(null);

        //如果Optional包含引用实例，返回true
        boolean present = possible.isPresent();
        //返回Optional所包含的引用，若引用缺失，则抛出IllegalStateException异常
        Integer get = possible.get();
        //返回Optional所包含的引用，若引用缺失，则返回指定的值
        Integer or = possible.or(10);
        //返回Optional所包含的引用，若引用缺失，则返回null
        Object o = nullable.orNull();

        //true
        System.out.println(possible.isPresent());
        //false
        System.out.println(absent.isPresent());
        //false
        System.out.println(nullable.isPresent());
        //2
        System.out.println(get);
        //2
        System.out.println(or);
        //null
        System.out.println(o);
    }
}
