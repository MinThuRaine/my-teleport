package com.my.teleport.system.order.service.domain.order.commandhandler;

import com.my.teleport.system.order.service.domain.order.dto.create.OrderCancelCommand;
import com.my.teleport.system.order.service.domain.order.dto.create.OrderCancelRespond;
import com.my.teleport.system.order.service.domain.order.mapper.OrderDataMapper;
import com.my.teleport.system.order.service.domain.order.ports.output.message.publisher.create.CancelOrderMessagePublisher;
import com.my.teleport.system.order.service.domain.order.ports.output.message.publisher.create.ProcessOrderMessagePublisher;
import com.my.teleport.system.order.service.domain.order.event.OrderCancelEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCancelCommandHandler {

    private final OrderHelper orderHelper;
    private final OrderDataMapper orderDataMapper;
    private final CancelOrderMessagePublisher cancelOrderMessagePublisher;

    public OrderCancelCommandHandler(OrderHelper orderHelper,
                                     OrderDataMapper orderDataMapper,
                                     CancelOrderMessagePublisher cancelOrderMessagePublisher
    ) {
        this.orderHelper = orderHelper;
        this.orderDataMapper = orderDataMapper;
        this.cancelOrderMessagePublisher = cancelOrderMessagePublisher;
    }

    @Transactional
    public OrderCancelRespond rejectOrder(OrderCancelCommand orderCancelCommand) {
        OrderCancelEvent orderCancelEvent = orderHelper.cancelOrder(orderCancelCommand);
        log.info("Order is cancelled with id: {}", orderCancelEvent.getOrder().getId().getValue());
        OrderCancelRespond orderCancelRespond = orderDataMapper.orderToCancelOrderResponse(orderCancelEvent.getOrder(),
                "Order cancelled successfully");

        //cancelOrderMessagePublisher.publish(orderCancelEvent);

        log.info("Returning CancelOrderRespond :: ", orderCancelRespond.toString());

        return orderCancelRespond;
    }
}
