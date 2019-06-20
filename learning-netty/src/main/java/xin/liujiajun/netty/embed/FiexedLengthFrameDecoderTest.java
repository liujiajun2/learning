package xin.liujiajun.netty.embed;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author LiuJiaJun
 * @date 2019/5/17 20:53
 */
public class FiexedLengthFrameDecoderTest {
    @Test
    public void testFrameDecoded(){
        ByteBuf buffer = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buffer.writeByte(i);
        }
        ByteBuf input = buffer.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FiexedLengthFrameDecoder(3));
        assertTrue(channel.writeInbound(input.retain()));

        assertTrue(channel.finish());

        ByteBuf read = (ByteBuf)channel.readInbound();
        assertEquals(buffer.readSlice(3),read);
        read.release();

        read = channel.readInbound();
        assertEquals(buffer.readSlice(3),read);
        read.release();

        read = channel.readInbound();
        assertEquals(buffer.readSlice(3),read);
        read.release();

        assertNull(channel.readInbound());
        buffer.release();
    }
}
