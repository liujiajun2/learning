package xin.liujiajun.mq.kafka.demo;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author liujiajun
 * @date 2019-12-03 19:17
 **/
public class FirstStartProduct {

    public static void main(String[] args) {
        Properties properties = new Properties();

        //bootstrap.servers 指定broker的地址清单，格式为host:port
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>("CustomerCountry","Precision","France" );



        //发送消息三种方式
        //1. 同步发送
        try {
            producer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2. 异步发送
        producer.send(record, (recordMetadata, e) -> {
            if (e != null) {
                e.printStackTrace();
            }else{
                System.out.println("发送成功");
            }
        });
        //3. 直接发送，不关心是否正常到达

        producer.send(record);

    }
}
