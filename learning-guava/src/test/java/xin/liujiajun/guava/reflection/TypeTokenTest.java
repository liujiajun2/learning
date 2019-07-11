package xin.liujiajun.guava.reflection;

import com.google.common.base.Function;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-20 11:39
 **/
public class TypeTokenTest {


    @Test
    public void test() throws NoSuchMethodException {
        List<Integer> list = Arrays.asList(1, 2, 3);

        System.out.println(list.getClass().getTypeName());

        TypeToken<? extends List> token = TypeToken.of(list.getClass());

        System.out.println(token.getType());

        TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {};

        System.out.println(typeToken.getType());

        System.out.println(typeToken.getRawType());

        TypeToken<List<String>>.TypeSet types = typeToken.getTypes();

        System.out.println(types);


        TypeToken<Map<String, Integer>> typeToken1 = new TypeToken<Map<String, Integer>>() {
        };
        TypeToken<?> entrySet = typeToken1.resolveType(Map.class.getMethod("entrySet").getGenericReturnType());

        System.out.println(entrySet);
    }

    @Test
    public void test01(){
        TypeToken<Integer> token = TypeToken.of(Integer.class);
        Type type = token.getType();
        System.out.println(type);

        TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {
        };
        System.out.println(typeToken.getType());

    }

    @Test
    public void test03(){

    }

    @Test
    public void test04(){

    }

    @Test
    public void test05(){
        TypeToken<Function<Integer, String>> funcToken = new TypeToken<Function<Integer, String>>() {};
        TypeToken<?> typeToken1 = funcToken.resolveType(Function.class.getTypeParameters()[1]);
//java.lang.String
        System.out.println(typeToken1);

    }

    @Test
    public void test06(){

    }


    static <K,V> TypeToken<Map<K,V>> mapToken(TypeToken<K> keyToken,TypeToken<V> valueToken){
        return new TypeToken<Map<K, V>>() {}
                .where(new TypeParameter<K>() {}, keyToken)
                .where(new TypeParameter<V>() {}, valueToken);
    }

    public static void main(String[] args) {
        TypeToken<Map<String, Integer>> mapTypeToken = mapToken(TypeToken.of(String.class), TypeToken.of(Integer.class));
// java.util.Map<java.lang.String, java.lang.Integer>
        System.out.println(mapTypeToken.getType());

    }

}
