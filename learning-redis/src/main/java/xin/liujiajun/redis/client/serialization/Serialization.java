package xin.liujiajun.redis.client.serialization;

import java.io.IOException;

/**
 * @author liujiajun
 * @date 2019-12-30 08:43
 **/
public interface Serialization {

    byte [] serialize(Object o) throws IOException;

    Object deserialize(byte [] bytes) throws IOException;

    Object deserialize(String str) throws IOException;
}
