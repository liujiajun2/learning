package xin.liujiajun.mq.kafka.demo;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author liujiajun
 * @date 2019-12-04 08:30
 **/
public class FirstStartComsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("group.id","country");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        //订阅主题
        consumer.subscribe(Collections.singletonList("CustomerCountry"));

        //消息轮询
        HashMap<String, Integer> map = new HashMap<>();
        try{
            while (true) {
                //poll 参数为0的话，会立即返回
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String,String> record : records) {
                    int updatedCount = 1;
                    if (map.containsValue(record.value())){
                        updatedCount = map.get(record.value()) + 1;
                    }
                    map.put(record.value(),updatedCount);

                    try{
                        //同步手动提交，会一直尝试直到提交成功
                        consumer.commitSync();
                        //异步提交，异步支持回调
//                        consumer.commitAsync();
                    }catch (CommitFailedException e) {

                    }

                }
            }
        }finally {
            consumer.close();
        }

        //提交和偏移量
        //1. 自动提交 默认enable.auto.commit 为true,每过5s,消费者会自动把从poll方法接收到的最大偏移量提交上去
        //提交时间间隔由auto.commit.interval.ms空指针，默认5s
        //close方法之前也会自动提交
        //自动提交会出现重复消息

        //提交当前偏移量
        //auto.commit.offset设为false,让应用程序决定何时提交偏移量

    }
}
