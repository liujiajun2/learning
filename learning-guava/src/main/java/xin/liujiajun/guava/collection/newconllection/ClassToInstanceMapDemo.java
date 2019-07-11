package xin.liujiajun.guava.collection.newconllection;

import com.google.common.collect.MutableClassToInstanceMap;

/**
 * @author liujiajun
 * @description ClassToInstanceMap demo
 * @create 2019-03-12 14:13
 **/
public class ClassToInstanceMapDemo {

    public static void main(String[] args) {
        MutableClassToInstanceMap<Number> number = MutableClassToInstanceMap.create();
        number.putInstance(Integer.class,0);
        number.putInstance(Integer.class,3);
        number.putInstance(Long.class,2L);
        Integer instance = number.getInstance(Integer.class);
        //3
        System.out.println(instance);
    }
}
