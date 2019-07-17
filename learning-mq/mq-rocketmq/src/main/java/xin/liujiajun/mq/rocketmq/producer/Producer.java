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
        DefaultMQProducer producer = new DefaultMQProducer("hello");
        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();;

        for (int i = 0; i < 100; i++) {
            Message message = new Message("topicTest", "TagA", ("hello RocketMq " + i).getBytes());
            SendResult sendResult = producer.send(message);

            System.out.println("result : "  + sendResult);

        }
        producer.shutdown();
    }
}
