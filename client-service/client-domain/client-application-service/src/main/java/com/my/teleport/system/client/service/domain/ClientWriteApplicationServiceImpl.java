package com.my.teleport.system.client.service.domain;

import com.my.teleport.system.client.service.domain.create.CreateClientCommand;
import com.my.teleport.system.client.service.domain.create.CreateClientResponse;
import com.my.teleport.system.client.service.domain.ports.input.ClientWriteApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class ClientWriteApplicationServiceImpl implements ClientWriteApplicationService {


    private final ClientCreateCommandHandler agentCreateCommandHandler;

    public ClientWriteApplicationServiceImpl(ClientCreateCommandHandler agentCreateCommandHandler) {
        this.agentCreateCommandHandler = agentCreateCommandHandler;
    }


    @Override
    public CreateClientResponse createClient(CreateClientCommand createClientCommand) {
        return agentCreateCommandHandler.createClient(createClientCommand);
    }


}
