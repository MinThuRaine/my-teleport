package com.my.teleport.system.client.service.domain.ports.input;

import com.my.teleport.system.client.service.domain.create.CreateClientCommand;
import com.my.teleport.system.client.service.domain.create.CreateClientResponse;

public interface ClientWriteApplicationService {

    CreateClientResponse createClient(CreateClientCommand createClientCommandCommand);

}
