package xin.liujiajun.guava.reflection;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author liujiajun
 * @description TypeToken demo
 * @create 2019-03-13 15:56
 **/
public class TypeTokenDemo {

    public static void main(String[] args) throws NoSuchMethodException {

        ArrayList<String> string = Lists.newArrayList();
        ArrayList<Integer> integers = Lists.newArrayList();

        //true   即使ArrayList<String> 不同于 ArrayList<Integer>
        System.out.println(string.getClass().isAssignableFrom(integers.getClass()));

        TypeToken<String> str = TypeToken.of(String.class);
        TypeToken<Integer> in = TypeToken.of(Integer.class);


        TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {};
        //java.util.List<java.lang.String>
        System.out.println(typeToken.getType());
        //interface java.util.List
        System.out.println(typeToken.getRawType());





        TypeToken<Map<String, Integer>> mapToken = new TypeToken<Map<String, Integer>>() {
        };

        TypeToken<?> entrySet = mapToken.resolveType(Map.class.getMethod("entrySet").getGenericReturnType());
        System.out.println(entrySet);


        TypeToken<Map<String, Integer>> mapTypeToken = mapToken(TypeToken.of(String.class), TypeToken.of(Integer.class));
        // java.util.Map<java.lang.String, java.lang.Integer>
        System.out.println(mapTypeToken.getType());


        TypeToken<Function<Integer, String>> funcToken = new TypeToken<Function<Integer, String>>() {};
        TypeToken<?> typeToken1 = funcToken.resolveType(Function.class.getTypeParameters()[1]);
        //java.lang.String
        System.out.println(typeToken1);
    }

    static <K,V> TypeToken<Map<K,V>> mapToken(TypeToken<K> keyToken,TypeToken<V> valueToken){
        return new TypeToken<Map<K, V>>() {}
                .where(new TypeParameter<K>() {}, keyToken)
                .where(new TypeParameter<V>() {}, valueToken);
    }
}
