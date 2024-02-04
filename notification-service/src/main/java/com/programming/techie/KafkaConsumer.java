package com.programming.techie;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

  @KafkaListener(
      topics = "notificationTopic",
      groupId = "notificationId",
      containerFactory = "orderPlacedEventKafkaListenerContainerFactory")
  public void orderPlacedEventListener(
      ConsumerRecord<String, OrderPlacedEvent> cr,
      @Payload OrderPlacedEvent orderPlacedEvent,
      @Headers MessageHeaders headers,
      Acknowledgment acknowledgment) {
    log.info(
        "Received message with key:{} and OrderPlacedEvent message: {}",
        cr.key(),
        orderPlacedEvent);
    // acknowledge
    acknowledgment.acknowledge();
  }
}
