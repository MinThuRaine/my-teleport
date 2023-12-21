package com.my.teleport.system.order.service.messaging.mapper;


import com.my.teleport.system.kafka.order.avro.model.OrderCreatedAvroModel;
import com.my.teleport.system.kafka.order.avro.model.OrderProcessingAvroModel;
import com.my.teleport.system.order.service.domain.order.entity.Order;
import com.my.teleport.system.order.service.domain.order.event.OrderProcessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class OrderMessagingDataMapper {

    public OrderCreatedAvroModel createOrderEventToCreateOrderPublishAvroModel(OrderProcessEvent orderProcessEvent) {
        Order order = orderProcessEvent.getOrder();

        return OrderCreatedAvroModel.newBuilder().setId(UUID.randomUUID().toString()).setOrderId(order.getId().getValue())
                .setClientId(order.getClientId().getValue())
                .build();
    }


    public OrderProcessingAvroModel processOrderEventToOrderProcessingAvroModel(OrderProcessEvent orderProcessEvent) {
        Order order = orderProcessEvent.getOrder();

        return OrderProcessingAvroModel.newBuilder().setId(UUID.randomUUID().toString()).setOrderId(order.getId().getValue())
                .setClientId(order.getClientId().getValue()).setCreatedAt(order.getProcessedDate().toInstant())
                .build();
    }

}
