package com.my.teleport.system.order.service.messaging.kafka;

import com.my.teleport.system.order.service.domain.order.event.OrderCreateEvent;
import com.my.teleport.system.order.service.domain.order.ports.output.message.publisher.create.CreateOrderMessagePublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderCreateKafkaMessagePublisher implements CreateOrderMessagePublisher {


    @Override
    public void publish(OrderCreateEvent domainEvent) {

    }
}
