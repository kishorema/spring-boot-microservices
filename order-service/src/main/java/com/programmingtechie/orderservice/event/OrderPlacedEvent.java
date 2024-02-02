package com.programmingtechie.orderservice.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class OrderPlacedEvent {
    private String orderNumber;

    public OrderPlacedEvent(Object source, String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderPlacedEvent(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
