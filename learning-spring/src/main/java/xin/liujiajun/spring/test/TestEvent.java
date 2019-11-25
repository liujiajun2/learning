package xin.liujiajun.spring.test;

import xin.liujiajun.spring.eventbus.BaseEvent;

/**
 * @author liujiajun
 * @date 2019-10-15 14:23
 **/
public class TestEvent implements BaseEvent {
    private String id;

    public String getId() {
        return id;
    }

    public TestEvent setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "TestEvent{" +
                "id='" + id + '\'' +
                '}';
    }
}
