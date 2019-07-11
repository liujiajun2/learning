package xin.liujiajun.springboot.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author liujiajun
 * @create 2019-07-10 11:33
 **/
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static int i = 0;

    /**
     * 什么是ApplicationContext?
     * 它是Spring的核心，Context我们通常解释为上下文环境，但是理解成容器会更好些。
     * ApplicationContext则是应用的容器。
     *
     * Spring把Bean（object）放在容器中，需要用就通过get方法取出来。
     */

    /**
     * 如果在上下文中部署一个实现了ApplicationListener接口的bean,
     *
     * 那么每当在一个ApplicationEvent发布到 ApplicationContext时，
     * 这个bean得到通知。其实这就是标准的Observer设计模式。
     *
     * 在这里的event是ApplicationReadyEvent ，应该是上下文加载好了之后
     *
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        System.out.println("MyApplicationListener" + MyApplicationListener.i++);
    }
}
