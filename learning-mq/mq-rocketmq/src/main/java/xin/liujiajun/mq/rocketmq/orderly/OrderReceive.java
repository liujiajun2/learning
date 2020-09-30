package xin.liujiajun.mq.rocketmq.orderly;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2020-09-30 09:03
 **/
public class OrderReceive {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestOrderlyConsumer");
        consumer.setNamesrvAddr("10.200.112.138:9876");

        consumer.subscribe("test_orderly", "*");

        final AtomicInteger lastIndex = new AtomicInteger(0);
        consumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg:list) {
                    String index = new String(msg.getBody());
                    int indexInt = Integer.parseInt(index);
                    System.out.println(Thread.currentThread().getName() + ": index=" + indexInt);
                    if (indexInt - lastIndex.get() != 1) {
                        System.out.println("error lastIndex=" + lastIndex.get()+",now index=" + indexInt);
                    }
                    lastIndex.set(indexInt);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.setConsumeThreadMin(2);
        consumer.start();
    }


}
