package xin.liujiajun.mq.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author liujiajun
 * @date 2019-12-17 13:46
 **/
public class PullConsumer {

    private static final Map<MessageQueue, Long> OFFSET_TABLE = new HashMap<MessageQueue, Long>();

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQPullConsumer pullConsumer = new DefaultMQPullConsumer("group");

        pullConsumer.setNamesrvAddr("127.0.0.1:9876");

        pullConsumer.start();

        Set<MessageQueue> mqs = pullConsumer.fetchSubscribeMessageQueues("hello");

        for (MessageQueue mq : mqs) {

            SINGLE_MQ:
            while (true) {
                PullResult pullResult = pullConsumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);

                putMessageQueueOffset(mq,pullResult.getNextBeginOffset());

                switch (pullResult.getPullStatus()) {
                    case FOUND:
                        break;
                    case NO_MATCHED_MSG:
                        break;
                    case NO_NEW_MSG:
                        break SINGLE_MQ;
                    case OFFSET_ILLEGAL:
                        break;
                    default:
                        break;
                }
            }
        }


    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = OFFSET_TABLE.get(mq);
        if (offset != null) {
            return offset;
        }

        return 0;
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        OFFSET_TABLE.put(mq, offset);
    }

}
