package xin.liujiajun.spring.test;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;
import xin.liujiajun.spring.eventbus.EventAdapter;

/**
 * @author liujiajun
 * @date 2019-10-15 14:24
 **/
@Component
public class TestAdapter extends EventAdapter<TestEvent> {


    @Subscribe
    @Override
    public void handle(TestEvent testEvent) {
        System.out.println("TestAdapter" + testEvent);
    }
}
