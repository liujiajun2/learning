package xin.liujiajun.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author liujiajun
 * @create 2019-07-09 12:52
 **/
public class HttpFileServer  {

    private static final String DEFAULT_URL = "/stc/com/netty";

    public void run(int port,String url) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                        //将多个消息转换为单一的FullHttpRequest或者FullHttpResponse
                        //原因是每个Http解码器在每个Http消息中会生成多个消息对象
                        //(1)HttpRequest / HttpResponse
                        //(2) HttpContent
                        //(3) LastHttpContent
                        socketChannel.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                        socketChannel.pipeline().addLast("http-encoder",new HttpRequestEncoder());
                        //支持异步发送大的码流（例如大的文件），但不占用过多的内存，防止发生内存溢出
                        socketChannel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                        socketChannel.pipeline().addLast("fileServerHandler",new HttpFileServerHandler());
                    }
                });
        ChannelFuture future = b.bind(port).sync();
        System.out.println("http 目录服务器启动" + "127.0.0.1:" + port);
        future.channel().closeFuture().sync();
    }
}
