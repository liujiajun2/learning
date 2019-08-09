package xin.liujiajun.netty.ssl.single;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author liujiajun
 * @date 2019-08-08 14:07
 **/
@ChannelHandler.Sharable
public class SingleSslClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("client read");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel active");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello".toCharArray(), CharsetUtil.UTF_8));
    }
}
