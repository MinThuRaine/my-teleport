package com.my.teleport.system.agent.service.domain;

import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.agent.service.domain.event.AgentCreateEvent;
import com.my.teleport.system.agent.service.domain.event.AgentUpdateStatusEvent;

public interface AgentDomainService {

AgentCreateEvent validateAndInitiateAgent(Agent agent);

AgentUpdateStatusEvent validateAndUpdateAgentStatus(Agent agent, Integer updatedStatus);


}
