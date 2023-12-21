package com.my.teleport.system.agent.service.domain;

import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentCommand;
import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentResponse;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusCommand;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusResponse;
import com.my.teleport.system.agent.service.domain.ports.input.AgentWriteApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class AgentWriteApplicationServiceImpl implements AgentWriteApplicationService {


    private final AgentCreateCommandHandler agentCreateCommandHandler;

    private final AgentUpdateCommandHandler agentUpdateCommandHandler;


    public AgentWriteApplicationServiceImpl(AgentCreateCommandHandler agentCreateCommandHandler, AgentUpdateCommandHandler agentUpdateCommandHandler) {
        this.agentCreateCommandHandler = agentCreateCommandHandler;
        this.agentUpdateCommandHandler = agentUpdateCommandHandler;
    }


    @Override
    public CreateAgentResponse createAgent(CreateAgentCommand createAgentCommand) {
        return agentCreateCommandHandler.createAgent(createAgentCommand);
    }

    @Override
    public UpdateAgentStatusResponse updateAgentStatus(UpdateAgentStatusCommand updateAgentStatusCommand) {
        return agentUpdateCommandHandler.updateAgentStatus(updateAgentStatusCommand);
    }


}
