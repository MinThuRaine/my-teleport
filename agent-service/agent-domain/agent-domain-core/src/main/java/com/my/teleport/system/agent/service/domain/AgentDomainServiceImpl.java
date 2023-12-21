package com.my.teleport.system.agent.service.domain;

import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.agent.service.domain.event.AgentCreateEvent;
import com.my.teleport.system.agent.service.domain.event.AgentUpdateStatusEvent;
import com.my.teleport.system.domain.valueobject.AgentStatus;
import com.my.teleport.system.domain.valueobject.ClientStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.my.teleport.system.domain.DomainConstants.UTC;

public class AgentDomainServiceImpl implements  AgentDomainService{


    @Override
    public AgentCreateEvent validateAndInitiateAgent(Agent agent) {

        agent.initializeAgent();

        return new AgentCreateEvent(agent, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public AgentUpdateStatusEvent validateAndUpdateAgentStatus(Agent agent, Integer updatedStatus) {

        agent.updateAgentStatus(AgentStatus.fromInt(updatedStatus));

        return new AgentUpdateStatusEvent(agent, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
