package xin.liujiajun.netty.redirect;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author liujiajun
 * @create 2019-07-23 12:34
 **/
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        System.out.println(o);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Packet packet = new Packet();
        packet.setCode(1);
        packet.setFlag(0);
        packet.setVersion(0);
        packet.setBody("{\"host\":\"127.0.0.1\",\"port\":5541}".getBytes());
        ctx.writeAndFlush(packet);
    }
}
