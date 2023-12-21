package com.my.teleport.system.client.service.messaging.mapper;


import com.my.teleport.system.client.service.domain.entity.Client;
import com.my.teleport.system.client.service.domain.event.ClientCreateEvent;
import com.my.teleport.system.kafka.client.avro.model.ClientCreatedAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ClientMessagingDataMapper {

    public ClientCreatedAvroModel createClientEventToCreateClientPublishAvroModel(ClientCreateEvent clientCreateEvent) {
        Client client = clientCreateEvent.getClient();

        return ClientCreatedAvroModel.newBuilder().setId(UUID.randomUUID().toString())
                .setClientId(client.getId().getValue())
                .setName(client.getName())
                .build();
    }

}
