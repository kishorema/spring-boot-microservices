package com.programming.techie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
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
      @Payload OrderPlacedEvent orderPlacedEvent, @Headers MessageHeaders headers) {
    log.info("Received OrderPlacedEvent message: {}", orderPlacedEvent);
  }
}
