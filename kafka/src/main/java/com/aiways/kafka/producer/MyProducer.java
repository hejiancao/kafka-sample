package com.aiways.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @author created by shaos on 2020/3/30
 */
public class MyProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        // ProducerConfig
        //kafka 集群，broker-list
        props.put("bootstrap.servers", "192.168.137.10:9092");
        // 等待所有副本节点的应答
        props.put("acks", "all");
        //重试次数
        props.put("retries", 1);
        //批次大小
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        // ProducerRecord(String topic, K key, V value)
        for (int i = 0; i < 10 ; i++) {
            //不带回调
            //producer.send(new ProducerRecord<String, String>("first", i+"", i +"" ));
            //带回调方法
            producer.send(new ProducerRecord<String, String>("second", i + "", i + ""), new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (recordMetadata != null) {
                        System.out.println("success::" + recordMetadata.partition() + "::" + recordMetadata.offset());
                    }else {
                        e.printStackTrace();
                    }
                }
            });
        }
        producer.close();
    }

}
