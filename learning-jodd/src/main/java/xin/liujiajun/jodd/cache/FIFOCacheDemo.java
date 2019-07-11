package xin.liujiajun.jodd.cache;

import jodd.cache.FIFOCache;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 13:48
 **/
public class FIFOCacheDemo {

    public static void main(String[] args) {

       // FIFOCach：先进先出缓存。优点是简单高效。缺点是不灵活，没有在内存中保存常用的缓存对象。
        FIFOCache<String, Integer> cache = new FIFOCache<String, Integer>(2);
        cache.put("a",1);
        cache.put("b",2);
        cache.put("c",3);

        Integer c = cache.get("c");
        Integer a = cache.get("a");
        //3
        System.out.println(c);
        //null
        System.out.println(a);
    }
}
