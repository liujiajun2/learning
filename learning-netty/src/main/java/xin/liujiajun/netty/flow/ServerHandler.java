package xin.liujiajun.netty.flow;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liujiajun
 * @create 2019-07-23 10:25
 **/
public class ServerHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("channelRead0");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        try(Entry entry = SphU.entry("HelloWorld")){
            System.out.println("hello world");
        }catch (BlockException e) {
            System.out.println("blocked!");
            ctx.channel().close();
        }
    }

}
