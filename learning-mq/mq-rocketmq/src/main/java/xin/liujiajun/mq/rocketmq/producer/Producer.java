package xin.liujiajun.mq.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author liujiajun
 * @create 2019-07-17 12:33
 **/
public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("default-group");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        String topic = "hello";
        String str = "hello";

        Message message = new Message(topic,str.getBytes());

        SendResult send = producer.send(message);

        System.out.println("send result " + send);

    }
}
