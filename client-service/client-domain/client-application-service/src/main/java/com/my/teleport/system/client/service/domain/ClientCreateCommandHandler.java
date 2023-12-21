package com.my.teleport.system.client.service.domain;


import com.my.teleport.system.client.service.domain.create.CreateClientCommand;
import com.my.teleport.system.client.service.domain.create.CreateClientResponse;
import com.my.teleport.system.client.service.domain.event.ClientCreateEvent;
import com.my.teleport.system.client.service.domain.mapper.ClientDataMapper;
import com.my.teleport.system.client.service.domain.ports.output.message.publisher.create.CreateClientMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ClientCreateCommandHandler {


    private final ClientCreateHelper clientCreateHelper;
    private final ClientDataMapper clientDataMapper;

    private final CreateClientMessagePublisher createClientMessagePublisher;

    public ClientCreateCommandHandler(ClientCreateHelper clientCreateHelper,
                                      ClientDataMapper clientDataMapper, CreateClientMessagePublisher createClientMessagePublisher
    ) {
        this.clientCreateHelper = clientCreateHelper;
        this.clientDataMapper = clientDataMapper;
        this.createClientMessagePublisher = createClientMessagePublisher;
    }

    @Transactional
    public CreateClientResponse createClient(CreateClientCommand createClientCommand) {
        ClientCreateEvent clientCreateEvent = clientCreateHelper.persistClient(createClientCommand);
        log.info("Client is created with id: {}", clientCreateEvent.getClient().getId().getValue());
        CreateClientResponse createClientResponse = clientDataMapper.clientToCreateClientResponse(clientCreateEvent.getClient(),
                "Client created successfully");

        createClientMessagePublisher.publish(clientCreateEvent);

        log.info("Returning CreateAgentResponse :: ", createClientResponse.toString());

        return createClientResponse;
    }

}
