package com.my.teleport.system.client.service.domain;

import com.my.teleport.system.client.service.domain.entity.Client;
import com.my.teleport.system.client.service.domain.event.ClientCreateEvent;

public interface ClientDomainService {

    ClientCreateEvent validateAndInitiateClient(Client client);

}
