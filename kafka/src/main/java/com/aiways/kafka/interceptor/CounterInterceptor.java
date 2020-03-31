package com.aiways.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author created by shaos on 2020/3/30
 */
public class CounterInterceptor implements ProducerInterceptor {

    int success;
    int error;

    public void configure(Map<String, ?> map) {

    }

    public ProducerRecord onSend(ProducerRecord producerRecord) {
        return producerRecord;
    }

    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (recordMetadata != null) {
            success++;
        } else {
            error++;
        }
    }

    public void close() {
        System.out.println("success->" + success);
        System.out.println("error->" + error);
    }


}
