package xin.liujiajun.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-20 08:44
 **/
public class BiMapTest {


    @Test
    public void test(){

        HashBiMap<String, Integer> map = HashBiMap.create();


        map.put("a",1);
        map.put("b",2);
        System.out.println(map.get("b"));


        BiMap<Integer, String> inverse = map.inverse();
        System.out.println(inverse.get(1));
    }
}
