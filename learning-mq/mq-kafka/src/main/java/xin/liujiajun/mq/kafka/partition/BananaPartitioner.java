package xin.liujiajun.mq.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;

import java.util.List;
import java.util.Map;

/**
 * @author liujiajun
 * @date 2019-12-03 20:30
 **/
public class BananaPartitioner  implements Partitioner {
    @Override
    public int partition(String s, Object key, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(s);
        int num = partitions.size();

        if ((bytes == null) || (!(key instanceof String))) {
            throw new InvalidRecordException("");
        }
        if (((String) key).equals("Banana")){
            return num;
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
