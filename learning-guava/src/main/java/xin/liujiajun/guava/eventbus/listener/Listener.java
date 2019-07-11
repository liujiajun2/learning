package xin.liujiajun.guava.eventbus.listener;

public interface Listener {

    /**
     * 监听器接口
     * @param e 事件e
     */
    void eventChanged(MyEvent e);
}
