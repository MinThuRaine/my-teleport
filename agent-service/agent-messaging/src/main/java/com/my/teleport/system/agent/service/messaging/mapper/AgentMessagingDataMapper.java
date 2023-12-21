package com.my.teleport.system.agent.service.messaging.mapper;


import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.agent.service.domain.event.AgentCreateEvent;
import com.my.teleport.system.kafka.agent.avro.model.AgentCreatedAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class AgentMessagingDataMapper {

    public AgentCreatedAvroModel createAgentEventToCreateAgentPublishAvroModel(AgentCreateEvent agentCreateEvent){
        Agent agent = agentCreateEvent.getAgent();

        return AgentCreatedAvroModel.newBuilder().setId(UUID.randomUUID().toString())
                .setAgentId(agent.getId().getValue().toString())
                .setName(agent.getName())
                .build();
    }

}
