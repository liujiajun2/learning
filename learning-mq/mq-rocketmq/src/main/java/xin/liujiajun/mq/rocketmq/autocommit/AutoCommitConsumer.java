package xin.liujiajun.mq.rocketmq.autocommit;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-11-25 11:12
 **/
public class AutoCommitConsumer {

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        try {
            testAutoCommit();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public static void testAutoCommit() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Auto_commit");

        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
                context.setAutoCommit(false);
                for (MessageExt ext : list) {
                    System.out.println(ext.toString());
                    long queueOffset = ext.getQueueOffset();
                    long commitLogOffset = ext.getCommitLogOffset();
                    System.out.println("queueOffset : " + queueOffset);
                    System.out.println("offset : " + commitLogOffset);

                    System.out.println( new String(ext.getBody()));
                    commit(consumer,context.getMessageQueue(),queueOffset + 1);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.subscribe("auto_commit","*");

        consumer.start();
    }

    public static void commit(DefaultMQPushConsumer consumer, MessageQueue messageQueue,long offset){

        executorService.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                consumer.getDefaultMQPushConsumerImpl().getOffsetStore().updateOffset(messageQueue,offset,false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
