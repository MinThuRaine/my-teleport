package com.my.teleport.system.client.service.domain;


import com.my.teleport.system.client.service.domain.create.CreateClientCommand;
import com.my.teleport.system.client.service.domain.entity.Client;
import com.my.teleport.system.client.service.domain.event.ClientCreateEvent;
import com.my.teleport.system.client.service.domain.mapper.ClientDataMapper;
import com.my.teleport.system.client.service.domain.ports.output.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ClientCreateHelper {

    ClientDomainService clientDomainService;
    ClientRepository clientRepository;
    ClientDataMapper clientDataMapper;


    public ClientCreateHelper(ClientDomainService clientDomainService,
                              ClientRepository clientRepository,
                              ClientDataMapper clientDataMapper) {
        this.clientDomainService = clientDomainService;
        this.clientRepository = clientRepository;
        this.clientDataMapper = clientDataMapper;

    }


    @Transactional
    public ClientCreateEvent persistClient(CreateClientCommand createClientCommand) {

        Client client = clientDataMapper.createClientCommandToClient(createClientCommand);

        ClientCreateEvent clientCreateEvent = clientDomainService.validateAndInitiateClient(client);

        saveClient(client);

        log.info("Client is created with id: {}", clientCreateEvent.getClient().getId().getValue());

        return clientCreateEvent;
    }


    private Client saveClient(Client client) {
        Client clientResult = clientRepository.save(client);

        if (clientResult == null) {
            log.error("Could not save client!");
        }

        log.info("Client is saved with id: {}", clientResult.getId().getValue());

        return clientResult;
    }

}
