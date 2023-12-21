package com.my.teleport.system.agent.service.domain.ports.output.message.publisher.create;

import com.my.teleport.system.agent.service.domain.event.AgentCreateEvent;
import com.my.teleport.system.domain.event.publisher.DomainEventPublisher;

public interface CreateAgentMessagePublisher extends DomainEventPublisher<AgentCreateEvent> {


}
