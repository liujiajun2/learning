package xin.liujiajun.jodd.cache;

import jodd.cache.LRUCache;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 13:51
 **/
public class LFUCacheDemo {

    public static void main(String[] args) {
        //LFUCache：最少访问次数缓存。优点是常用缓存保留在内存中，偶然会使扫描算法失效。
        // 缺点是大的获取消耗即这个算法不能快速适应变化的使用模式，特别是集群的临时获取是无效的。

        LRUCache<String, Integer> cache = new LRUCache<>(2);

        cache.put("a",1);
        cache.put("b",2);

        //访问两次 a
        cache.get("a");
        cache.get("a");

        cache.put("c",3);

        //1
        System.out.println(cache.get("a"));
        //null
        System.out.println(cache.get("b"));
        //3
        System.out.println(cache.get("c"));

    }
}
