package com.programming.techie;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/** In this class we'll add all the manual configuration required for Observability to work. */
@Configuration
@EnableKafka
public class OrderPlacedConsumerConfiguration {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  public ConsumerFactory<String, OrderPlacedEvent> orderPlacedEventConsumerFactory() {

    JsonDeserializer<OrderPlacedEvent> deserializer =
        new JsonDeserializer<>(OrderPlacedEvent.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);

    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "notificationId");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, OrderPlacedEvent>
      orderPlacedEventKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, OrderPlacedEvent> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    factory.setConsumerFactory(orderPlacedEventConsumerFactory());
    return factory;
  }
}
