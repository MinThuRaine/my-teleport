package com.my.teleport.system.client.service.dataaccess.client.adapter;

import com.my.teleport.system.client.service.dataaccess.client.mapper.ClientDataAccessMapper;
import com.my.teleport.system.client.service.dataaccess.client.repository.ClientJpaRepository;
import com.my.teleport.system.client.service.domain.entity.Client;
import com.my.teleport.system.client.service.domain.ports.output.repository.ClientRepository;
import org.springframework.stereotype.Component;


@Component
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientDataAccessMapper clientDataAccessMapper;

    public ClientRepositoryImpl(ClientJpaRepository clientJpaRepository,
                                ClientDataAccessMapper agentDataAccessMapper) {
        this.clientJpaRepository = clientJpaRepository;
        this.clientDataAccessMapper = agentDataAccessMapper;
    }


    @Override
    public Client save(Client client) {

        return clientDataAccessMapper.clientEntityToClient(
                clientJpaRepository.save(clientDataAccessMapper.clientToClientEntity(client)));

    }
}
