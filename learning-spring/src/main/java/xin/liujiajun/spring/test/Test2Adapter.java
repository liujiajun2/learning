package xin.liujiajun.spring.test;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;
import xin.liujiajun.spring.eventbus.EventAdapter;

import java.lang.reflect.Method;

/**
 * @author liujiajun
 * @date 2019-10-15 14:31
 **/
@Component
public class Test2Adapter extends EventAdapter<TestEvent> {


    @Subscribe
    @Override
    public void handle(TestEvent testEvent) {
        System.out.println("Test2Adapter" + testEvent);
    }

}