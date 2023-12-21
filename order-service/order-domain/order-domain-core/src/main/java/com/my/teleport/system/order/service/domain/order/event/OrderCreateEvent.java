package com.my.teleport.system.order.service.domain.order.event;

import com.my.teleport.system.order.service.domain.order.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreateEvent extends OrderEventProcessor {

    public OrderCreateEvent(Order order, ZonedDateTime createdDate) {
        super(order, createdDate);
    }
}
