package com.my.teleport.system.order.service.domain.order.commandhandler;

import com.my.teleport.system.order.service.domain.order.dto.create.OrderProcessingCommand;
import com.my.teleport.system.order.service.domain.order.dto.create.OrderProcessingRespond;
import com.my.teleport.system.order.service.domain.order.mapper.OrderDataMapper;
import com.my.teleport.system.order.service.domain.order.ports.output.message.publisher.create.ProcessOrderMessagePublisher;
import com.my.teleport.system.order.service.domain.order.event.OrderProcessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class processOrderCommandHandler {

    private final OrderHelper orderHelper;
    private final OrderDataMapper orderDataMapper;

    private final ProcessOrderMessagePublisher processOrderMessagePublisher;

    public processOrderCommandHandler(OrderHelper orderHelper,
                                      OrderDataMapper orderDataMapper,
                                      ProcessOrderMessagePublisher processOrderMessagePublisher
    ) {
        this.orderHelper = orderHelper;
        this.orderDataMapper = orderDataMapper;
        this.processOrderMessagePublisher = processOrderMessagePublisher;
    }

    @Transactional
    public OrderProcessingRespond processOrder(OrderProcessingCommand orderProcessingCommand) {
        OrderProcessEvent orderProcessEvent = orderHelper.processOrder(orderProcessingCommand);
        log.info("Order is Processing with id: {}", orderProcessEvent.getOrder().getId().getValue());
        OrderProcessingRespond orderProcessingRespond = orderDataMapper.orderToProcessOrderResponse(orderProcessEvent.getOrder(),
                "Order Processing successfully");

        //createOrderMessagePublisher.publish(orderConfirmEvent);

        log.info("Returning OrderProcessingRespond :: ", orderProcessingRespond.toString());

        return orderProcessingRespond;
    }
}
