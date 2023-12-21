package com.my.teleport.system.order.service.domain.order;

import com.my.teleport.system.order.service.domain.order.entity.Order;
import com.my.teleport.system.order.service.domain.order.event.OrderProcessEvent;
import com.my.teleport.system.order.service.domain.order.event.OrderCreateEvent;
import com.my.teleport.system.order.service.domain.order.event.OrderCancelEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.my.teleport.system.domain.DomainConstants.UTC;

public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public OrderCreateEvent validateAndInitiateOrder(Order order) {



        order.initializeOrder();

        return new OrderCreateEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));

    }

    @Override
    public OrderProcessEvent processOrder(Order order) {

        order.orderProcessing();

        return new OrderProcessEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));

    }

    @Override
    public OrderCancelEvent cancelOrder(Order order) {

        order.orderCancelled();

        return new OrderCancelEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));

    }





}
