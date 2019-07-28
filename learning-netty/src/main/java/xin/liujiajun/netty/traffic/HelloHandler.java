package xin.liujiajun.netty.traffic;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author liujiajun
 * @create 2019-07-25 19:29
 **/
public class HelloHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("hello");
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8));
//        channelHandlerContext.channel().writeAndFlush("hello");
    }
}
