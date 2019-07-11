package xin.liujiajun.guava.collection;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 15:02
 **/
public class NewCollectionTest {

    @Test
    public void test01(){
        List<String> list = Arrays.asList("a", "b", "c", "a", "b", "a", "c", "d");
//传统方式
        Map<String, Integer> counts = new HashMap<>(16);
        for (String word: list) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word,1);
            }else {
                counts.put(word,count +1);
            }
            // jdk1.8 可以写成这样  counts.merge(word, 1, (a, b) -> a + b);
        }
// 3
        System.out.println(counts.get("a"));

//multiset
        HashMultiset<String> strings1 = HashMultiset.create(list);
// 3
        System.out.println(strings1.count("a"));

    }

    @Test
    public void test03(){

    }

    @Test
    public void test04(){
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a",1);
        multimap.put("a",2);
        multimap.put("a",4);
        multimap.put("a",4);
        multimap.put("b",3);
        multimap.put("c",5);
        List<Integer> a = multimap.get("a");
//[1, 2, 4, 4]
        System.out.println(a);
        a.remove(1);
//{a=[1, 4, 4], b=[3], c=[5]}
        System.out.println(multimap);
//[3]
        List<Integer> b = multimap.get("b");
        System.out.println(multimap.get("b"));
//[]
        System.out.println(multimap.get("d"));
        List<Integer> c = multimap.replaceValues("c", Arrays.asList(7, 8, 9));
//[5]
        System.out.println(c);
//[7, 8, 9]
        System.out.println(multimap.get("c"));
        Map<String, Collection<Integer>> stringCollectionMap = multimap.asMap();

    }

    @Test
    public void test05(){
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
        //Integer bob = user.forcePut("Bob", 1);
      //  System.out.println(user);
//12
        //System.out.println(bob);

        BiMap<Integer, String> inverse = user.inverse();
//Bob
        System.out.println(inverse);
//{Tony=2, Bob=1, Jack=21}
//        System.out.println(user);

    }

    @Test
    public void test06(){
        Table<Integer, Integer, String> table = HashBasedTable.create();
        table.put(0,0,"1");
        table.put(0,1,"2");
        table.put(0,2,"3");
        table.put(1,0,"4");
        table.put(1,1,"5");
        table.put(1,2,"6");
        table.put(2,0,"7");
        table.put(2,1,"8");
        table.put(2,2,"9");

        Map<Integer, Map<Integer, String>> integerMapMap = table.rowMap();
//{0={0=1, 1=2, 2=3}, 1={0=4, 1=5, 2=6}, 2={0=7, 1=8, 2=9}}
        System.out.println(integerMapMap);
        Map<Integer, String> row = table.row(1);
        System.out.println(row);
        Range.closed("aa","bb");

        TreeRangeSet<Comparable<?>> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed("刚刚","方法"));
        rangeSet.add(Range.closed("aa","cc"));

//        rangeSet.remove(Range.closed("方法","刚刚"));
        System.out.println(rangeSet);
    }

}
