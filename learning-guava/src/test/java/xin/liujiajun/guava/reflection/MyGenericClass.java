package xin.liujiajun.guava.reflection;

import com.google.common.reflect.TypeToken;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-20 13:58
 **/
public class MyGenericClass<T> {

    private TypeToken<T> recordType;

    public MyGenericClass(){
        this.recordType = new TypeToken<T>(getClass()) {};
    }

    protected Class<T> getRecordType(){

        return (Class<T>) recordType.getRawType();
    }


    public static void main(String[] args) {
        MyGenericClass<String> clazz = new MyGenericClass<String>(){};
        System.out.println(clazz.getRecordType());
    }
}
