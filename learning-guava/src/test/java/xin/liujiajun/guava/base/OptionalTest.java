package xin.liujiajun.guava.base;


import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class OptionalTest {

    @Test
    public void testMakeOptional() {
        //创建一个包含给定值得Optional，或者快速失败
        Optional<Integer> of = Optional.of(1);
        System.out.println(of);

        //java.lang.NullPointerException
        //Optional<Object> of1 = Optional.of(null);

        //创建一个值不存在的Optional
        Optional<Object> absent = Optional.absent();
        System.out.println(absent);

        //创建一个可能为null值得Option,如果值为null相当于absent
        Optional<Object> fromNullable = Optional.fromNullable(null);
        System.out.println(fromNullable);
    }

    @Test
    public void testMethod() {

        Optional<Integer> optional = Optional.of(2);
        //判断Optional的值是否不为null
        if (optional.isPresent()) {
            //返回所包含的实例，如果所包含的实例为null，直接抛出异常
            System.out.println(optional.get());
        }

        Optional<Object> nullable = Optional.fromNullable(null);
        Object or = nullable.or(2);
        System.out.println(or);


        Object o = nullable.orNull();
        System.out.println(o);


        Set<Object> objects = nullable.asSet();
        System.out.println(objects);
    }


    @Test
    public void test02() {
        java.util.Optional<Object> empty = java.util.Optional.empty();
        System.out.println(empty.isPresent());

    }
    @Test
    public void test01(){
        //Optional.of(T) 创建制定引用的Optional实例，如果引用为null则快速失败
        Optional<Integer> possible = Optional.of(2);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());
        Optional<Object> nullable = Optional.fromNullable(null);
        System.out.println(nullable.isPresent());
        System.out.println(nullable.get());
//        // 创建引用缺失的Optional实例
//        Optional<Object> absent = Optional.absent();
//        // 创建制定引用的Optional实例，若引用为null则表示缺失
//        Optional<Object> nullable = Optional.fromNullable(null);
//
//        //如果Optional包含引用实例，返回true
//        boolean present = possible.isPresent();
//        //返回Optional所包含的引用，若引用缺失，则抛出IllegalStateException异常
//        Integer get = possible.get();
//        //返回Optional所包含的引用，若引用缺失，则返回指定的值
//        Integer or = possible.or(10);
//        //返回Optional所包含的引用，若引用缺失，则返回null
//        Object o = nullable.orNull();
//
//        //true
//        System.out.println(possible.isPresent());
//        //false
//        System.out.println(absent.isPresent());
//        //false
//        System.out.println(nullable.isPresent());
//        //2
//        System.out.println(get);
//        //2
//        System.out.println(or);
//        //null
//        System.out.println(o);

    }

    @Test
    public void test03(){
        Preconditions.checkNotNull(null,"null");
    }

    @Test
    public void test04(){

        List<String> list = Arrays.asList("a", "f", "e", "dfadf");
        //[a, f, e, dfadf]
        System.out.println(list);
        list.sort(Ordering.<String>natural());
        //[a, dfadf, e, f]
        System.out.println(list);

    }

    @Test
    public void test05(){
        try{
            throw new OutOfMemoryError();
        }catch (Throwable throwable) {
            //异常的传播
            //抛出异常，当throwabel 为declaredType类型的实例
            //Throwables.throwIfInstanceOf(throwable, NullPointerException.class);

            //here will throw java.lang.OutOfMemoryError
            //Throwables.throwIfUnchecked(throwable);

             Throwables.propagateIfPossible(throwable,ArrayIndexOutOfBoundsException.class);
        }
    }

    @Test
    public void test06(){
        try {
            throw new NullPointerException();
        }catch (Throwable t){
            //异常原因链

            Throwable rootCause = Throwables.getRootCause(t);
            //null
            System.out.println(rootCause.getCause());

            List<Throwable> causalChain = Throwables.getCausalChain(t);
            //[java.lang.NullPointerException]
            System.out.println(causalChain);

            String stackTraceAsString = Throwables.getStackTraceAsString(t);
            //java.lang.NullPointerException
            System.out.println(stackTraceAsString);
        }

    }



}
