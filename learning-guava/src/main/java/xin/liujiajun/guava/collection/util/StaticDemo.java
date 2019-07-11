package xin.liujiajun.guava.collection.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author liujiajun
 * @description static demo
 * @create 2019-03-12 15:02
 **/
public class StaticDemo {

    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList();
        LinkedHashMap<String, Integer> maps = Maps.newLinkedHashMap();
        //初始化制定元素
        ArrayList<String> list1 = Lists.newArrayList("a","a","f","e");
        //初始化大小
        ArrayList<String> list2 = Lists.newArrayListWithCapacity(100);
        //新集合类型没有暴露原始构造器，
        HashMultiset<Object> multiset = HashMultiset.create();


    }
}
