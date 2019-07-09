package xin.liujiajun.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import xin.liujiajun.netty.http.HttpFileServerHandler;

/**
 * @author liujiajun
 * @create 2019-07-09 19:21
 **/
public class WebSocketServer {

    public void run(int port) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("http-codec",new HttpServerCodec());
                        //将多个消息转换为单一的FullHttpRequest或者FullHttpResponse
                        //原因是每个Http解码器在每个Http消息中会生成多个消息对象
                        //(1)HttpRequest / HttpResponse
                        //(2) HttpContent
                        //(3) LastHttpContent
                        socketChannel.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                        //支持异步发送大的码流（例如大的文件），但不占用过多的内存，防止发生内存溢出
                        socketChannel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                        socketChannel.pipeline().addLast("handler",new WebSocketServerHandler());
                    }
                });
        ChannelFuture future = b.bind(port).sync();
        System.out.println("http 目录服务器启动" + "127.0.0.1:" + port);
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        WebSocketServer webSocketServer = new WebSocketServer();
        webSocketServer.run(8080);
    }
}
