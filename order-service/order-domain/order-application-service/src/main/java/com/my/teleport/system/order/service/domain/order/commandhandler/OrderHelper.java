package com.my.teleport.system.order.service.domain.order.commandhandler;


import com.my.teleport.system.domain.valueobject.OrderStatus;
import com.my.teleport.system.order.service.domain.order.OrderDomainService;
import com.my.teleport.system.order.service.domain.order.entity.Order;
import com.my.teleport.system.order.service.domain.order.entity.OrderLifeCycleStateMachine;
import com.my.teleport.system.order.service.domain.order.entity.ServiceAgentOrderLifecycleStateMachine;
import com.my.teleport.system.order.service.domain.order.dto.create.OrderProcessingCommand;
import com.my.teleport.system.order.service.domain.order.dto.create.CreateOrderCommand;
import com.my.teleport.system.order.service.domain.order.dto.create.OrderCancelCommand;
import com.my.teleport.system.order.service.domain.order.event.OrderProcessEvent;
import com.my.teleport.system.order.service.domain.order.event.OrderCreateEvent;
import com.my.teleport.system.order.service.domain.order.event.OrderCancelEvent;
import com.my.teleport.system.order.service.domain.order.mapper.OrderDataMapper;
import com.my.teleport.system.order.service.domain.order.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class OrderHelper {

    OrderDomainService orderDomainService;
    OrderRepository orderRepository;
    OrderDataMapper orderDataMapper;


    public OrderHelper(OrderDomainService orderDomainService,
                       OrderRepository orderRepository,
                       OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.orderDataMapper = orderDataMapper;

    }

    @Transactional
    public OrderProcessEvent processOrder(OrderProcessingCommand orderProcessingCommand) {

        Order order = orderRepository.getById(orderProcessingCommand.getOrderId());

        order.setHelper(defaultOrderLifecycleStateMachine());

        OrderProcessEvent orderConfirmEvent = orderDomainService.processOrder(order);


        Boolean updateResult = orderRepository.processOrder(order);

        if (updateResult == false) {
            log.error("Could not update order status!");
        }

        log.info("Order is updated with id: {} , status: {}", order.getId().getValue(), order.getOrderStatus().getValue());


        return orderConfirmEvent;
    }

    @Transactional
    public OrderCancelEvent cancelOrder(OrderCancelCommand orderCancelCommand) {

        Order order = orderRepository.getById(orderCancelCommand.getOrderId());

        order.setHelper(defaultOrderLifecycleStateMachine());

        OrderCancelEvent orderRejectEvent = orderDomainService.cancelOrder(order);


        Boolean updateResult = orderRepository.cancelOrder(order);

        if (updateResult == false) {
            log.error("Could not update order status!");
        }

        log.info("Order is updated with id: {} , status: {}", order.getId().getValue(), order.getOrderStatus().getValue());


        log.info("Order is cancelled with id: {}", orderRejectEvent.getOrder().getId().getValue());

        return orderRejectEvent;
    }


    @Transactional
    public OrderCreateEvent persistOrder(CreateOrderCommand createOrderCommand) {

        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);

        order.setHelper(defaultOrderLifecycleStateMachine());


        OrderCreateEvent orderCreateEvent = orderDomainService.validateAndInitiateOrder(order);

        saveOrder(order);

        log.info("Order is created with id: {}", orderCreateEvent.getOrder().getId().getValue());

        return orderCreateEvent;
    }


    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);

        if (orderResult == null) {
            log.error("Could not save order!");
        }

        log.info("Order is saved with id: {}", orderResult.getId().getValue());

        return orderResult;
    }

    private OrderLifeCycleStateMachine defaultOrderLifecycleStateMachine() {
        final List<OrderStatus> allowedLoanStatuses = Arrays.asList(OrderStatus.values());
        return new ServiceAgentOrderLifecycleStateMachine(allowedLoanStatuses);
    }

}
