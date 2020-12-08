package xin.liujiajun.websocket.conf;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liujiajun
 * @date 2020-12-08 09:56
 **/
@Configuration
public class AppConfig {


    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration conf = new com.corundumstudio.socketio.Configuration();
        conf.setHostname("localhost");
        conf.setPort(10001);
        conf.setOrigin("*");
//        conf.setTransports(Transport.WEBSOCKET);

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpKeepAlive(false);
        socketConfig.setTcpNoDelay(true);

        conf.setSocketConfig(socketConfig);

        SocketIOServer server = new SocketIOServer(conf);
        //也可以通过代码方式add监听器，也可以通过注解方式，目前采用注解方式
//        server.addConnectListener(null);
        return server;
    }
    /**
     * 通过注解来实现扫描各种事件
     *
     * @param socketServer
     * @return
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

}
