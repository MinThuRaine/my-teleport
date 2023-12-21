package com.my.teleport.system.client.service.domain.event;

import com.my.teleport.system.client.service.domain.entity.Client;
import com.my.teleport.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public abstract class ClientEvent implements DomainEvent<Client> {

    private final Client client;

    private final ZonedDateTime createdAt;

    public ClientEvent(Client client, ZonedDateTime createdAt) {
        this.client = client;
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
