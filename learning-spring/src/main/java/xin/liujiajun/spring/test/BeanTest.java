package xin.liujiajun.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xin.liujiajun.spring.bean.Animal;
import xin.liujiajun.spring.bean.HelloBean;
import xin.liujiajun.spring.eventbus.EventBusFacade;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author LiuJiaJun
 * @date 2019/6/13 0:03
 */
public class BeanTest {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        HelloBean hello = (HelloBean)context.getBean("hello");
        Object o = context.getBean("hello");
        Map<String, Animal> beans = context.getBeansOfType(Animal.class);
        Set<String> strings = beans.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(o);
        hello.say();

        testEventBus();

        ((ClassPathXmlApplicationContext) context).refresh();
    }

    public static void testEventBus(){
        EventBusFacade.execute(new TestEvent().setId("tttt"));
        EventBusFacade.execute(new TestPostEvent().setName("TestPostEvent"));
    }
}
