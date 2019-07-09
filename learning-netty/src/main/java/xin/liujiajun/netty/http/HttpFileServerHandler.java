package xin.liujiajun.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

/**
 * @author liujiajun
 * @create 2019-07-09 12:56
 **/
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {
        if (request.getDecoderResult().isSuccess()) {
            return ;
        }
        if (request.getMethod() != HttpMethod.GET){
            return ;
        }
        String uri = request.getUri();

    }


}
