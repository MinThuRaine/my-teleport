package com.my.teleport.system.order.service.domain.order.event;

import com.my.teleport.system.domain.event.DomainEvent;
import com.my.teleport.system.order.service.domain.order.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderEventProcessor implements DomainEvent<Order> {

    private final Order order;

    private final ZonedDateTime createdAt;

    public OrderEventProcessor(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
