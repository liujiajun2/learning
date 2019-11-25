package xin.liujiajun.spring.eventbus;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author liujiajun
 * @date 2019-10-15 14:02
 **/
@Configuration
public class EventBusConfiguration implements InitializingBean , DisposableBean {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String,EventAdapter> beans = null;

    @Override
    public void destroy() throws Exception {
        if (beans != null) {
            for (EventAdapter eventAdapter : beans.values()) {
                EventBusFacade.unregister(eventAdapter);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        beans = applicationContext.getBeansOfType(EventAdapter.class);
        if (beans != null) {
            for (EventAdapter eventAdapter : beans.values()) {
                EventBusFacade.register(eventAdapter);
            }
        }
    }
}
