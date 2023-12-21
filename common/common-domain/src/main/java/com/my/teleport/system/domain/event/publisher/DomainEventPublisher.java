package com.my.teleport.system.domain.event.publisher;


import com.my.teleport.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
