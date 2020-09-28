package xin.liujiajun.netty.group;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author liujiajun
 * @date 2020-02-18 09:26
 **/
public class ClientInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LineBasedFrameDecoder(1024));
        pipeline.addLast(new LoggingHandler());

        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new StringDecoder());

        pipeline.addLast(new ClientHandler());

    }
}
