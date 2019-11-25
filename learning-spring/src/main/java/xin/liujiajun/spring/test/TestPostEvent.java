package xin.liujiajun.spring.test;

import xin.liujiajun.spring.eventbus.BaseEvent;

/**
 * @author liujiajun
 * @date 2019-10-25 10:13
 **/
public class TestPostEvent implements BaseEvent {

    private String name;

    public String getName() {
        return name;
    }

    public TestPostEvent setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "TestPostEvent{" +
                "name='" + name + '\'' +
                '}';
    }
}
