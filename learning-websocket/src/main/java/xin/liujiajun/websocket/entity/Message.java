package xin.liujiajun.websocket.entity;

/**
 * @author liujiajun
 * @date 2020-12-08 13:58
 **/
public class Message {

    private String msgContent;

    public String getMsgContent() {
        return msgContent;
    }

    public Message setMsgContent(String msgContent) {
        this.msgContent = msgContent;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgContent='" + msgContent + '\'' +
                '}';
    }
}
