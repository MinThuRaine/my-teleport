package com.my.teleport.system.agent.service.domain;


import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentCommand;
import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentResponse;
import com.my.teleport.system.agent.service.domain.event.AgentCreateEvent;
import com.my.teleport.system.agent.service.domain.mapper.AgentDataMapper;
import com.my.teleport.system.agent.service.domain.ports.output.message.publisher.create.CreateAgentMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class AgentCreateCommandHandler {

    private final AgentHelper agentCreateHelper;
    private final AgentDataMapper agentDataMapper;

    private final CreateAgentMessagePublisher createAgentMessagePublisher;



    public AgentCreateCommandHandler(AgentHelper agentCreateHelper,
                                     AgentDataMapper agentDataMapper, CreateAgentMessagePublisher createAgentMessagePublisher
                                    ) {
        this.agentCreateHelper = agentCreateHelper;
        this.agentDataMapper = agentDataMapper;
        this.createAgentMessagePublisher = createAgentMessagePublisher;
    }

    @Transactional
    public CreateAgentResponse createAgent(CreateAgentCommand createAgentCommand) {
        AgentCreateEvent agentCreateEvent = agentCreateHelper.persistAgent(createAgentCommand);
        log.info("Agent is created with id: {}", agentCreateEvent.getAgent().getId().getValue());
        CreateAgentResponse createAgentResponse = agentDataMapper.agentToCreateAgentResponse(agentCreateEvent.getAgent(),
                "Agent created successfully");

        //createAgentMessagePublisher.publish(agentCreateEvent);

        log.info("Returning CreateAgentResponse :: ", createAgentResponse.toString() );

        return createAgentResponse;
    }

}
