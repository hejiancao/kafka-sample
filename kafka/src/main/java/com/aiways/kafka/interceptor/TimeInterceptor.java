package com.aiways.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author created by shaos on 2020/3/30
 */
public class TimeInterceptor implements ProducerInterceptor{

    public void configure(Map<String, ?> map) {

    }

    public ProducerRecord onSend(ProducerRecord producerRecord) {
        ProducerRecord record = new ProducerRecord(producerRecord.topic(), producerRecord.partition(),
                producerRecord.key(), System.currentTimeMillis() + " -> " + producerRecord.value());
        return record;
    }

    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    public void close() {

    }


}
