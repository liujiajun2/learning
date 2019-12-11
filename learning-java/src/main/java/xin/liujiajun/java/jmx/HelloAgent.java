package xin.liujiajun.java.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-12-11 09:10
 **/
public class HelloAgent {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        //ObjectName中的取名是有一定规范的，格式为：“域名：name=MBean名称”，
        // 其中域名和MBean的名称可以任意取。
        // 这样定义后，就可以唯一标识我们定义的这个MBean的实现类了。
        ObjectName helloBean = new ObjectName("jmxBean:name=hello");
        server.registerMBean(new Hello(),helloBean);

        TimeUnit.MINUTES.sleep(10);

    }
}
