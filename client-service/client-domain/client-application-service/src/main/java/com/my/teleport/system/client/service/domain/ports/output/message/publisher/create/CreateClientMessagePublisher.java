package com.my.teleport.system.client.service.domain.ports.output.message.publisher.create;

import com.my.teleport.system.client.service.domain.event.ClientCreateEvent;
import com.my.teleport.system.domain.event.publisher.DomainEventPublisher;

public interface CreateClientMessagePublisher extends DomainEventPublisher<ClientCreateEvent> {


}
