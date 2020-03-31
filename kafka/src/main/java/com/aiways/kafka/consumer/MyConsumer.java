package com.aiways.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author created by shaos on 2020/3/30
 */
public class MyConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //ConsumerConfig
        // 定义kakfa 服务的地址，不需要将所有broker指定上
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.137.10:9092");
        // 制定consumer group
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        // 是否自动确认offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 自动确认offset的时间间隔
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // key的序列化类
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化类
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // 消费者订阅的topic, 可同时订阅多个
        consumer.subscribe(Arrays.asList("second"));
        while (true) {
            // 读取数据，读取超时时间为100ms
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}
