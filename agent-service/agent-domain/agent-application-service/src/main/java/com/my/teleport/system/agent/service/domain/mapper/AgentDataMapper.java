package com.my.teleport.system.agent.service.domain.mapper;


import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentCommand;
import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentResponse;
import com.my.teleport.system.agent.service.domain.dto.query.AgentDataResponse;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusResponse;
import com.my.teleport.system.agent.service.domain.entity.Agent;
import org.springframework.stereotype.Component;

@Component
public class AgentDataMapper {

    public Agent createAgentCommandToAgent(CreateAgentCommand createAgentCommand) {
        return Agent.builder().name(createAgentCommand.getName())
                .identityNumber(createAgentCommand.getIdentityNumber())
                .age(createAgentCommand.getAge()).phoneNo(createAgentCommand.getPhoneNo()).build();
    }

    public CreateAgentResponse agentToCreateAgentResponse(Agent agent, String message){
        return CreateAgentResponse.builder().agentId(agent.getId().getValue())
                .agentStatus(agent.getStatus()).message(message).build();
    }

    public UpdateAgentStatusResponse agentToUpdateAgentStatusResponse(Agent agent, String message){
        return UpdateAgentStatusResponse.builder().agentId(agent.getId().getValue())
                .message(message).build();
    }



    public AgentDataResponse agentToAgentDataResponse(Agent agent){
        return AgentDataResponse.builder().name(agent.getName()).age(agent.getAge())
                .identityNumber(agent.getIdentityNumber()).phoneNo(agent.getPhoneNo())
                .build();
    }




}
