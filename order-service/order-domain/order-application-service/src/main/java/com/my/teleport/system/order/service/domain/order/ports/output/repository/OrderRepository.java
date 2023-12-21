package com.my.teleport.system.order.service.domain.order.ports.output.repository;

import com.my.teleport.system.order.service.domain.order.entity.Order;

public interface OrderRepository {

    Order save(Order order);

    Boolean processOrder(Order order);

    Boolean cancelOrder(Order order);

    Order getById(String id);

}
