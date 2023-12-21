package com.my.teleport.system.order.service.domain.order.event;

import com.my.teleport.system.order.service.domain.order.entity.Order;

import java.time.ZonedDateTime;

public class OrderProcessEvent extends OrderEventProcessor {

    public OrderProcessEvent(Order order, ZonedDateTime createdDate) {
        super(order, createdDate);
    }
}
