package xin.liujiajun.netty.connect;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-08-07 15:12
 **/
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()->{
            int i = 0;
            boolean printed = false;
            while (true) {
                i++;
                if (i == 100000) {
                    try {
                        TimeUnit.SECONDS.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                Channel channel = ctx.channel();
                boolean writable = ctx.channel().isWritable();
                if (writable) {
                    printed = false;
                    ctx.channel().writeAndFlush("0000000000000000000000000000");
                }else{
                    if (!printed) {
                        printed = true;
                        System.out.println("[writeAndFlush] write disable  " + channel.id());
                    }
                }
            }
        },1,10, TimeUnit.SECONDS);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        Channel channel = ctx.channel();
        if (channel.isWritable()) {
            System.out.println("[channelWritabilityChanged] write able " + channel.id());
        }else{
            System.out.println("[channelWritabilityChanged] write disable  " + channel.id());
        }

    }
}
