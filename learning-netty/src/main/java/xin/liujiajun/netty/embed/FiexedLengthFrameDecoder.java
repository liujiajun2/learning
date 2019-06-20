package xin.liujiajun.netty.embed;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author LiuJiaJun
 * @date 2019/5/17 20:50
 */
public class FiexedLengthFrameDecoder extends ByteToMessageDecoder {
    private final int frameLength;

    public FiexedLengthFrameDecoder(int frameLength) {
        if(frameLength <= 0) {
            throw new IllegalArgumentException("length must be over 0");
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() >= frameLength) {
            ByteBuf buf = byteBuf.readBytes(frameLength);
            list.add(buf);
        }
    }
}
