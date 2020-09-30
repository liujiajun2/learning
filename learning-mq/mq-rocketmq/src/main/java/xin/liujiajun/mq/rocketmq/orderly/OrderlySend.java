package xin.liujiajun.mq.rocketmq.orderly;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2020-09-30 08:58
 **/
public class OrderlySend {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("TestOrderlyProducer");

        producer.setNamesrvAddr("10.200.112.138:9876");

        producer.start();

        String topic = "test_orderly";

        int count = 5000;

        CountDownLatch countDownLatch = new CountDownLatch(count);
        AtomicInteger index = new AtomicInteger(0);
        for (int i = 1; i <= count; i++) {
            Message message = new Message(topic,String.valueOf(i).getBytes());
            producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    return list.get((int) o);
                }
            }, 0, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    index.incrementAndGet();
                    countDownLatch.countDown();
//                    System.out.println("success" + sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    countDownLatch.countDown();
                    System.out.println("error");
                    throw new RuntimeException(throwable);
                }
            });
        }
        countDownLatch.await();
        System.out.println("send success: " + index.get());
        producer.shutdown();
    }
}
