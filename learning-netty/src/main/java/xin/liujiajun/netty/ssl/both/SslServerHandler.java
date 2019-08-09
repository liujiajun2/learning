package xin.liujiajun.netty.ssl.both;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.SslHandshakeCompletionEvent;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;
import java.security.Principal;

/**
 * @author liujiajun
 * @date 2019-08-08 14:07
 **/
@ChannelHandler.Sharable
public class SslServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("server read");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channel active");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("事件");
        if (evt instanceof SslHandshakeCompletionEvent) {
            getPeerCertificates(ctx);
        }
    }

    private void getPeerCertificates(ChannelHandlerContext ctx){
        SslHandler handler = (SslHandler)ctx.channel().pipeline().get("ssl");
        try {
            X509Certificate[] chain = handler.engine().getSession().getPeerCertificateChain();
            if(chain != null) {
                X509Certificate certificate = chain[0];
                if (certificate != null) {
                    Principal subjectDN = certificate.getSubjectDN();
                    String name = subjectDN.getName();
                    System.out.println(name);
                    System.out.println(subjectDN.toString());
                }
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        }

    }
}
