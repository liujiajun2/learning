package xin.liujiajun.mq.rocketmq.producer;

import com.google.protobuf.ByteString;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author liujiajun
 * @create 2019-07-17 12:33
 **/
public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer-group");
        producer.setNamesrvAddr("10.200.112.77:9876");

        producer.start();
        String msg = "{\"ip\":\"10.85.1.7\",\"mac\":\"1c:1b:0d:49:7c:fe\",\"os\":\"Windows 7 Professional (7601)\",\"name\":\"ZQS 开发电脑\",\"enterpriseId\":\"5it6faj5\",\"model\":\"MVC900\",\"source\":\"usb\",\"reportTime\":1563507459.0,\"devices\":[{\"id\":\"FEDCBA9876543239\",\"model\":\"CP900\",\"firmware\":\"100.0.0.25\",\"hardware\":\"100.0.6.0.0.0.0\",\"status\":\"offline\",\"type\":\"audio\",\"name\":\"YL1425-A01889PC\",\"reportTime\":1563507604.0}]}";


        Message.MqMsg messge = Message.MqMsg.newBuilder()
                .setVersion("1.0" + "")
                .setType(96)
                .setSessionId("")
                .setFinFlag(false)
                .setMac("1c1b0d497cfe")
                .setChannelId("")
                .setDataType(1)
                .setFileName("")
                .setFileType("")
                .setContent(ByteString.copyFrom(msg.getBytes()))
                .build();
        org.apache.rocketmq.common.message.Message deviceReport = new org.apache.rocketmq.common.message.Message("device_report_e", messge.toByteArray());

        SendResult sendResult = producer.send(deviceReport, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, org.apache.rocketmq.common.message.Message message, Object o) {
                Integer id = (Integer) o;
                return list.get(id);
            }
        },1);

        System.out.println("result : "  + sendResult);
        producer.shutdown();
    }
}
