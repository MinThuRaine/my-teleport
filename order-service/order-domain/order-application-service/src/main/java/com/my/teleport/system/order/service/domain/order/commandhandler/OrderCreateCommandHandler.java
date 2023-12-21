package com.my.teleport.system.order.service.domain.order.commandhandler;


import com.my.teleport.system.order.service.domain.order.ports.output.message.publisher.create.CreateOrderMessagePublisher;
import com.my.teleport.system.order.service.domain.order.ports.output.message.publisher.create.ProcessOrderMessagePublisher;
import com.my.teleport.system.order.service.domain.order.dto.create.CreateOrderCommand;
import com.my.teleport.system.order.service.domain.order.dto.create.CreateOrderResponse;
import com.my.teleport.system.order.service.domain.order.event.OrderCreateEvent;
import com.my.teleport.system.order.service.domain.order.mapper.OrderDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCreateCommandHandler {


    private final OrderHelper orderCreateHelper;
    private final OrderDataMapper orderDataMapper;

    private final CreateOrderMessagePublisher createOrderMessagePublisher;

    public OrderCreateCommandHandler(OrderHelper orderCreateHelper,
                                     OrderDataMapper orderDataMapper,
                                     CreateOrderMessagePublisher createOrderMessagePublisher
    ) {
        this.orderCreateHelper = orderCreateHelper;
        this.orderDataMapper = orderDataMapper;
        this.createOrderMessagePublisher = createOrderMessagePublisher;
    }



    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreateEvent orderCreateEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("Order is created with id: {}", orderCreateEvent.getOrder().getId().getValue());
        CreateOrderResponse createOrderResponse = orderDataMapper.orderToCreateOrderResponse(orderCreateEvent.getOrder(),
                "Order created successfully");

        createOrderMessagePublisher.publish(orderCreateEvent);

        log.info("Returning CreateOrderResponse :: ", createOrderResponse.toString());

        return createOrderResponse;
    }

}
