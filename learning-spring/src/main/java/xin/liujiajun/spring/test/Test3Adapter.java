package xin.liujiajun.spring.test;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;
import xin.liujiajun.spring.eventbus.EventAdapter;

/**
 * @author liujiajun
 * @date 2019-10-25 10:14
 **/
@Component
public class Test3Adapter extends EventAdapter<TestPostEvent> {

    @Subscribe
    @Override
    public void handle(TestPostEvent testPostEvent) {
        System.out.println("Test3Adapter" + testPostEvent);
    }
}
