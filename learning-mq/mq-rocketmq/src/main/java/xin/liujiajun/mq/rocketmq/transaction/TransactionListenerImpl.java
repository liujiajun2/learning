package xin.liujiajun.mq.rocketmq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2019-11-25 19:52
 **/
public class TransactionListenerImpl implements TransactionListener {

    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String,Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 该方法主要是设置本地事务状态，与业务代码在同一事务中。
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(message.getTransactionId(),status);
        System.out.println("执行本地方法");
        return LocalTransactionState.UNKNOW;
    }

    /**
     * 该方法主要是告知RocketMQ消息是需要提交还是回滚；
     * RocketMQ会按一定的频率回查事务，查询有次数限制，默认5次。
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        Integer status = localTrans.get(messageExt.getTransactionId());
        System.out.println("回查： " + messageExt.getMsgId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
                    return LocalTransactionState.COMMIT_MESSAGE;
            }
        }

        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
