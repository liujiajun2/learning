package xin.liujiajun.websocket.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xin.liujiajun.websocket.entity.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liujiajun
 * @date 2020-12-08 10:05
 **/
@Component
public class Recipient {

    private static final Logger logger = LoggerFactory.getLogger(Recipient.class);

    @Autowired
    private SocketIOServer server;
    private Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        logger.info("session is : {}", client.getSessionId());

        clientMap.put(client.getSessionId().toString(), client);

    }

    @OnDisconnect
    public void onDisConnect(SocketIOClient client) {
        logger.info("session disconnect : {}",client.getSessionId());
        clientMap.remove(client.getSessionId().toString());
    }

    @OnEvent(value = "message")
    public void doMessage(SocketIOClient client, AckRequest request, Message data) {

        logger.info("data : {}",data);

    }
}
