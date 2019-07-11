package xin.liujiajun.netty.protocol;

/**
 * @author liujiajun
 * @create 2019-07-10 12:44
 **/
public class NettyMessage {

    private Header header;
    private Object body;

    public Header getHeader() {
        return header;
    }

    public NettyMessage setHeader(Header header) {
        this.header = header;
        return this;
    }

    public Object getBody() {
        return body;
    }

    public NettyMessage setBody(Object body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
