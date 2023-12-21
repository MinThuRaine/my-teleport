package com.my.teleport.system.client.service.domain.event;

import com.my.teleport.system.client.service.domain.entity.Client;

import java.time.ZonedDateTime;

public class ClientCreateEvent extends ClientEvent {

    public ClientCreateEvent(Client client, ZonedDateTime createdDate) {
        super(client, createdDate);
    }
}
