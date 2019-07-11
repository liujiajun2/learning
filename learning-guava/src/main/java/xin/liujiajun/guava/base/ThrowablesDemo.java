package xin.liujiajun.guava.base;

import com.google.common.base.Throwables;

import java.util.List;

/**
 * @author liujiajun
 * @description Throwables demo
 * @create 2019-03-11 17:49
 **/
public class ThrowablesDemo {

    public static void main(String[] args) {

        try{
            throw new OutOfMemoryError();
        }catch (Throwable throwable){
            //异常的传播
            //抛出异常，当throwabel 为declaredType类型的实例
            Throwables.throwIfInstanceOf(throwable,NullPointerException.class);

            //here will throw java.lang.OutOfMemoryError
            Throwables.throwIfUnchecked(throwable);

           // Throwables.propagateIfPossible(throwable,ArrayIndexOutOfBoundsException.class);
        }



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
