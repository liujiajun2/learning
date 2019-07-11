package xin.liujiajun.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

import java.util.List;
import java.util.Map;

/**
 * @author liujiajun
 * @create 2019-07-10 12:47
 **/
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() {
//        DefaultMarshallerProvider defaultMarshallerProvider = new DefaultMarshallerProvider();
//        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage nettyMessage, List<Object> list) throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null) {
            throw new Exception("encode message is null");
        }
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(nettyMessage.getHeader().getCrcCode());
        buffer.writeInt(nettyMessage.getHeader().getLength());
        buffer.writeLong(nettyMessage.getHeader().getSessoinId());
        buffer.writeByte(nettyMessage.getHeader().getType());
        buffer.writeByte(nettyMessage.getHeader().getPriority());
        buffer.writeInt(nettyMessage.getHeader().getAttachement().size());
        String key = null;
        byte [] keyArray = null;
        Object value = null;
        Map<String, Object> attachement = nettyMessage.getHeader().getAttachement();
        for (Map.Entry<String,Object> param : attachement.entrySet()){
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            buffer.writeInt(keyArray.length);
            buffer.writeBytes(keyArray);
            value = param.getValue();

//            marshallingEncoder.
        }
    }
}
