package com.my.teleport.system.order.service.domain.order;

import com.my.teleport.system.order.service.domain.order.entity.Order;
import com.my.teleport.system.order.service.domain.order.event.OrderProcessEvent;
import com.my.teleport.system.order.service.domain.order.event.OrderCreateEvent;
import com.my.teleport.system.order.service.domain.order.event.OrderCancelEvent;

public interface OrderDomainService {

        OrderCreateEvent validateAndInitiateOrder(Order order);

        OrderProcessEvent processOrder(Order order);

        OrderCancelEvent cancelOrder(Order order);



}
