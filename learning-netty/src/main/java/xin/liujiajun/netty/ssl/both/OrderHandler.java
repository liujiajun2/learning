package xin.liujiajun.netty.ssl.both;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author LiuJiaJun
 * @date 2019/11/13 22:18
 */
@ChannelHandler.Sharable
public class OrderHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel In Active");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel in Inactive");
        ctx.fireChannelInactive();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("read");
        channelHandlerContext.fireChannelRead(o);
    }
}
