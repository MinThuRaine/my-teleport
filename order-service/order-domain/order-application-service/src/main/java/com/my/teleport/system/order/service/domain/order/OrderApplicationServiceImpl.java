package com.my.teleport.system.order.service.domain.order;

import com.my.teleport.system.order.service.domain.order.commandhandler.processOrderCommandHandler;
import com.my.teleport.system.order.service.domain.order.commandhandler.OrderCreateCommandHandler;
import com.my.teleport.system.order.service.domain.order.commandhandler.OrderCancelCommandHandler;
import com.my.teleport.system.order.service.domain.order.dto.create.*;
import com.my.teleport.system.order.service.domain.order.ports.input.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {


    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final processOrderCommandHandler orderConfirmCommandHandler;
    private final OrderCancelCommandHandler orderRejectCommandHandler;


    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler, processOrderCommandHandler orderConfirmCommandHandler, OrderCancelCommandHandler orderRejectCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderConfirmCommandHandler = orderConfirmCommandHandler;
        this.orderRejectCommandHandler = orderRejectCommandHandler;
    }


    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public OrderProcessingRespond processOrder(OrderProcessingCommand orderProcessingCommand) {
        return orderConfirmCommandHandler.processOrder(orderProcessingCommand);
    }

    @Override
    public OrderCancelRespond cancelOrder(OrderCancelCommand orderCancelCommand) {
        return orderRejectCommandHandler.rejectOrder(orderCancelCommand);
    }

}
