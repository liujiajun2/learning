package xin.liujiajun.guava.reflection;

import com.google.common.reflect.TypeToken;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-20 14:34
 **/
public class MyTypeTokenDemo<T> {

    private TypeToken<T> recordType;

    public MyTypeTokenDemo(){
        this.recordType = new TypeToken<T>(getClass()) {};
    }

    protected Class<T> getRecordType(){

        return (Class<T>) recordType.getRawType();
    }


    public static void main(String[] args) {
        MyTypeTokenDemo<Integer> token = new MyTypeTokenDemo<Integer>() {};
        //class java.lang.Integer
        System.out.println(token.getRecordType());

    }
}
