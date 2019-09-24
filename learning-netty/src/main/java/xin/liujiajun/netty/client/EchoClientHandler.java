package xin.liujiajun.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuJiaJun
 * @date 2019/5/14 13:25
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static String str = "";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("hello", CharsetUtil.UTF_8));
        new Thread(()->{
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ctx.channel().isWritable()){
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello", CharsetUtil.UTF_8));
                }
            }
        }).start();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received :" + byteBuf.toString(CharsetUtil.UTF_8));


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    public static String getStr(){
        if ("".equals(str)){
            for (int i = 0; i < 1024 * 1024; i++) {
                str += "a";
            }
        }
        return str;
    }

}
