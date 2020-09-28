package xin.liujiajun.netty.group;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liujiajun
 * @date 2020-02-18 09:33
 **/
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] bytes = ("hello server!!!" + System.getProperty("line.separator")).getBytes();
        ByteBuf message = Unpooled.buffer(bytes.length).writeBytes(bytes);
        ctx.channel().writeAndFlush(message);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        logger.info("receive msg from server : {}", msg);
    }
}
