package com.my.teleport.system.order.service.domain.order.event;

import com.my.teleport.system.order.service.domain.order.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelEvent extends OrderEventProcessor {

    public OrderCancelEvent(Order order, ZonedDateTime createdDate) {
        super(order, createdDate);
    }
}
