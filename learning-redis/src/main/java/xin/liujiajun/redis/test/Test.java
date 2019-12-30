package xin.liujiajun.redis.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liujiajun
 * @date 2019-12-30 12:57
 **/
@Component
public class Test {
    @Autowired
    private TestCache testCache;

    @PostConstruct
    public void init(){
        testCache.put("hello","world");

        String hello = (String)testCache.get("hello");
        System.out.println(hello);
    }
}
