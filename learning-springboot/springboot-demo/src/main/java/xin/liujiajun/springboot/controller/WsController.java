package xin.liujiajun.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import xin.liujiajun.springboot.modle.WiselyMessage;
import xin.liujiajun.springboot.modle.WiselyResponse;

import java.security.Principal;

/**
 * @author LiuJiaJun
 * @date 2019/5/31 10:23
 */
@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/welcome")
    @SendTo("topic/getResponse")
    public WiselyResponse say(WiselyMessage message){
        return new WiselyResponse("welcome," + message.getName());
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal,String msg){
        if (principal.getName().equals("aa")){
            simpMessagingTemplate.convertAndSendToUser("bb","/queue/notifications",
                    principal.getName() + "，send：" + msg);
        }else {
            simpMessagingTemplate.convertAndSendToUser("aa","/queue/notifications",
                    principal.getName() + "，send：" + msg);

        }
    }
}
