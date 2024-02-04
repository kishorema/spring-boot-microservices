package com.programming.techie;

import java.io.Serializable;

public class OrderPlacedEvent implements Serializable {
  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  @Override
  public String toString() {
    return "OrderPlacedEvent{" + "orderNumber='" + orderNumber + '\'' + '}';
  }

  private String orderNumber;

  public OrderPlacedEvent() {}
}
