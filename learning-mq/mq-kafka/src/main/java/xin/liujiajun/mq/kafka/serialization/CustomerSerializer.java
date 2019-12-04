package xin.liujiajun.mq.kafka.serialization;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author liujiajun
 * @date 2019-12-03 20:06
 **/
public class CustomerSerializer implements Serializer<Customer> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, Customer customer) {
        byte[] serializedName;
        int stringSize;
        if (customer == null) {
            return null;
        }else{
            if (customer.getCustomerName() != null) {
                serializedName = customer.getCustomerName().getBytes(StandardCharsets.UTF_8);
                stringSize = serializedName.length;
            }else{
                serializedName = new byte[0];
                stringSize = 0;
            }
        }
        ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);

        buffer.putInt(customer.getCustomerId());
        buffer.putInt(stringSize);
        buffer.put(serializedName);

        return buffer.array();
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Customer data) {
        return this.serialize(topic,data);
    }

    @Override
    public void close() {

    }
}
