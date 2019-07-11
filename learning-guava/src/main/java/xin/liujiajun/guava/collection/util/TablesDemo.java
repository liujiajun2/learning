package xin.liujiajun.guava.collection.util;

import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;

import java.util.Map;

/**
 * @author liujiajun
 * @description Tables demo
 * @create 2019-03-12 17:10
 **/
public class TablesDemo {

    public static void main(String[] args) {
        Table<String, Character, Integer> table = Tables.newCustomTable(
                Maps.<String, Map<Character, Integer>>newLinkedHashMap(),
                new Supplier<Map<Character, Integer>>() {
                    @Override
                    public Map<Character, Integer> get() {
                        return Maps.newLinkedHashMap();
                    }
                });
    }
}
