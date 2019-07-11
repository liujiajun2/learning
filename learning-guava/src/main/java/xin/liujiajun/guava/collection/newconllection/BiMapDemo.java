package xin.liujiajun.guava.collection.newconllection;

import com.google.common.collect.HashBiMap;

import java.util.HashMap;

/**
 * @author liujiajun
 * @description BiMap demp
 * @create 2019-03-12 13:41
 **/
public class BiMapDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Joy",1);
        hashMap.put("Bob",12);
        hashMap.put("Tony",2);
        hashMap.put("Jack",21);
        HashBiMap<String, Integer> user = HashBiMap.create();
        user.putAll(hashMap);
        //java.lang.IllegalArgumentException: value already present: 1
        //Integer bob = user.put("Bob", 1);
        //forcePut 强制更换已经存在的值，会把原来存在的键-值删除，
        Integer bob = user.forcePut("Bob", 1);
        //12
        System.out.println(bob);
        String s = user.inverse().get(1);
        //Bob
        System.out.println(s);
        //{Tony=2, Bob=1, Jack=21}
        System.out.println(user);
    }
}
