package com.programmingtechie.orderservice.config;

import com.programmingtechie.orderservice.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * In this class we'll add all the manual configuration required for Observability to
 * work.
 */
@Configuration
@RequiredArgsConstructor
public class ManualConfiguration {

    @Bean

    // Method
    public ProducerFactory<String, OrderPlacedEvent> producerFactory()
    {

        // Creating a Map
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "host.docker.internal:9092");
        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    // Annotation
    @Bean

    // Method
    public KafkaTemplate kafkaTemplate()
    {

        return new KafkaTemplate<>(producerFactory());
    }

}
