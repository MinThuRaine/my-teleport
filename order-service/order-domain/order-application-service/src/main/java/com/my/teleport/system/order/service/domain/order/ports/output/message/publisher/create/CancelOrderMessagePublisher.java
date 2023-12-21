package com.my.teleport.system.order.service.domain.order.ports.output.message.publisher.create;

import com.my.teleport.system.domain.event.publisher.DomainEventPublisher;
import com.my.teleport.system.order.service.domain.order.event.OrderProcessEvent;

public interface CancelOrderMessagePublisher extends DomainEventPublisher<OrderProcessEvent> {


}
