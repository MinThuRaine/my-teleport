package com.my.teleport.system.taskorder.service.dataaccess.order.adapter;

import com.my.teleport.system.taskorder.service.dataaccess.order.entity.OrderEntity;
import com.my.teleport.system.taskorder.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.my.teleport.system.taskorder.service.dataaccess.order.repository.OrderJpaRepository;
import com.my.teleport.system.order.service.domain.order.entity.Order;
import com.my.teleport.system.order.service.domain.order.ports.output.repository.OrderRepository;
import org.springframework.stereotype.Component;


@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository,
                               OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(
                orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Order getById(String id) {
        OrderEntity orderEntity = orderJpaRepository.getById(id);
        orderEntity.initializeLazyCollections();
        return orderDataAccessMapper.orderEntityToOrder(orderEntity);
    }

    @Override
    public Boolean processOrder(Order order) {
        Integer result = orderJpaRepository.processOrder(order.getOrderStatus().getValue(), order.getId().getValue(), order.getProcessedDate());

        return result > 0;
    }

    @Override
    public Boolean cancelOrder(Order order) {
        Integer result = orderJpaRepository.cancelOrder(order.getOrderStatus().getValue(), order.getId().getValue(), order.getCancelledDated());
        return result > 0;
    }


}
