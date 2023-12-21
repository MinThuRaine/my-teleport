package com.my.teleport.system.agent.service.domain.ports.input;

import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentCommand;
import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentResponse;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusCommand;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusResponse;

public interface AgentWriteApplicationService {

CreateAgentResponse createAgent(CreateAgentCommand createAgentCommand);

UpdateAgentStatusResponse updateAgentStatus(UpdateAgentStatusCommand updateAgentStatusCommand);

}
