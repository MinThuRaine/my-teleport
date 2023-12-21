package com.my.teleport.system.client.service.domain.ports.output.repository;

import com.my.teleport.system.client.service.domain.entity.Client;

public interface ClientRepository {

    Client save(Client client);

}
