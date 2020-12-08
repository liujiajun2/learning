package xin.liujiajun.websocket.start;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author liujiajun
 * @date 2020-12-08 10:02
 **/
@Component
public class ServerStart implements CommandLineRunner {


    private static final Logger logger = LoggerFactory.getLogger(ServerStart.class);

    @Autowired
    private SocketIOServer server;

    @Override
    public void run(String... args) throws Exception {
        try {
            server.start();
        }catch (Exception e) {
            logger.error("SocketIOServer start exception:",e);
        }

    }
}
