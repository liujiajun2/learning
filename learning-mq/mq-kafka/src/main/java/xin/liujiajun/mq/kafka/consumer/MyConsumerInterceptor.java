package xin.liujiajun.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujiajun
 * @date 2020-01-09 19:14
 **/
public class MyConsumerInterceptor implements ConsumerInterceptor<String,String> {

    private static final long EXPIRE_TIME = 10 * 1000L;
    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
        long now = System.currentTimeMillis();
        Map<TopicPartition, List<ConsumerRecord<String, String>>> newRecords = new HashMap<>();
        //遍历分区数据
        for (TopicPartition tp : consumerRecords.partitions()) {
            //获取分区数据
            List<ConsumerRecord<String, String>> tpRecords = consumerRecords.records(tp);
            List<ConsumerRecord<String, String>> newTpRecords = new ArrayList<>();
            //遍历分区中的每一条数据
            for (ConsumerRecord<String, String> record : tpRecords) {
                //时间是否符合
                if (now - record.timestamp() < EXPIRE_TIME) {
                    newTpRecords.add(record);
                }
            }
            if (!newTpRecords.isEmpty()) {
                newRecords.put(tp,newTpRecords);
            }
        }
        return new ConsumerRecords<>(newRecords);
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
        map.forEach((tp,offset)->{
            System.out.println(tp + ": " + offset);
        });
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
