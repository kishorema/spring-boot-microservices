package com.programmingtechie.orderservice.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPlacedEvent {
  private String orderNumber;

  public OrderPlacedEvent() {}

  public OrderPlacedEvent(String orderNumber) {
    this.orderNumber = orderNumber;
  }
}
