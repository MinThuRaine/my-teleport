package com.my.teleport.system.client.service.domain;

import com.my.teleport.system.client.service.domain.entity.Client;
import com.my.teleport.system.client.service.domain.event.ClientCreateEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.my.teleport.system.domain.DomainConstants.UTC;

public class ClientDomainServiceImpl implements ClientDomainService {


    @Override
    public ClientCreateEvent validateAndInitiateClient(Client client) {

        client.initializeClient();

        return new ClientCreateEvent(client, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
