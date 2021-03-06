package xin.liujiajun.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author LiuJiaJun
 * @date 2019/5/30 13:19
 */
@Service
@ServerEndpoint("/websocket/{uid}")
public class WebSocketService {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid){
        this.session = session;
        logger.info("uid : {}",uid);
    }

    @OnClose
    public void onClose(){
        logger.info("关闭");
    }

    @OnMessage
    public void onMessage(String message, Session session){
        logger.info(message);
    }
}
