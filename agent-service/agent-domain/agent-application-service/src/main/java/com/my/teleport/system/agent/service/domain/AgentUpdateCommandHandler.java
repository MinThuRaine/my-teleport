package com.my.teleport.system.agent.service.domain;

import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusCommand;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusResponse;
import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.agent.service.domain.event.AgentUpdateStatusEvent;
import com.my.teleport.system.agent.service.domain.mapper.AgentDataMapper;
import com.my.teleport.system.domain.valueobject.AgentId;
import com.my.teleport.system.domain.valueobject.AgentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Component
public class AgentUpdateCommandHandler {

    private final AgentHelper agentCreateHelper;
    private final AgentDataMapper agentDataMapper;


    public AgentUpdateCommandHandler(AgentHelper agentCreateHelper,
                                     AgentDataMapper agentDataMapper
    ) {
        this.agentCreateHelper = agentCreateHelper;
        this.agentDataMapper = agentDataMapper;
    }


    @Transactional
    public UpdateAgentStatusResponse updateAgentStatus(UpdateAgentStatusCommand updateAgentStatusCommand) {

        AgentUpdateStatusEvent agentUpdateStatusEvent = agentCreateHelper.updateAgentStatus(updateAgentStatusCommand);
        log.info("Agent is created with id: {}", agentUpdateStatusEvent.getAgent().getId().getValue());
        UpdateAgentStatusResponse updateAgentStatusResponse = agentDataMapper.agentToUpdateAgentStatusResponse(agentUpdateStatusEvent.getAgent(),
                "Agent Status Update successfully");

        //createAgentMessagePublisher.publish(agentCreateEvent);

        log.info("Returning UpdateAgentResponse :: ", updateAgentStatusResponse.toString());


        return updateAgentStatusResponse;
    }


}
