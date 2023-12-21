package com.my.teleport.system.agent.service.domain.ports.input;

import com.my.teleport.system.agent.service.domain.dto.query.AgentDataQuery;
import com.my.teleport.system.agent.service.domain.dto.query.AgentDataResponse;

public interface AgentReadApplicationService {

    AgentDataResponse getAgentByID(AgentDataQuery agentDataQuery);


}
