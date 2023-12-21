package com.my.teleport.system.agent.service.domain;


import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentCommand;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusCommand;
import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.agent.service.domain.event.AgentCreateEvent;
import com.my.teleport.system.agent.service.domain.event.AgentUpdateStatusEvent;
import com.my.teleport.system.agent.service.domain.mapper.AgentDataMapper;
import com.my.teleport.system.agent.service.domain.ports.output.repository.AgentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class AgentHelper {

    AgentDomainService agentDomainService;
    AgentRepository agentRepository;
    AgentDataMapper agentDataMapper;


    public AgentHelper(AgentDomainService agentDomainService,
                       AgentRepository agentRepository,
                       AgentDataMapper agentDataMapper) {
        this.agentDomainService = agentDomainService;
        this.agentRepository = agentRepository;
        this.agentDataMapper = agentDataMapper;

    }


    @Transactional
    public AgentCreateEvent persistAgent(CreateAgentCommand createAgentCommand){

        Agent agent = agentDataMapper.createAgentCommandToAgent(createAgentCommand);

        AgentCreateEvent agentCreateEvent = agentDomainService.validateAndInitiateAgent(agent);

        saveAgent(agent);

        log.info("Agent is created with id: {}", agentCreateEvent.getAgent().getId().getValue());

        return agentCreateEvent;
    }


    private Agent saveAgent(Agent agent){
        Agent agentResult = agentRepository.save(agent);

        if(agentResult == null){
            log.error("Could not save agent!");
        }

        log.info("Agent is saved with id: {}", agentResult.getId().getValue());

        return agentResult;
    }


    @Transactional
    public AgentUpdateStatusEvent updateAgentStatus(UpdateAgentStatusCommand command){

        Agent agent = agentRepository.findAgentWithNotFoundDetection(command.getAgentId());

        AgentUpdateStatusEvent agentUpdateStatusEvent = agentDomainService.validateAndUpdateAgentStatus(agent,command.getStatus());


        int agentResult = agentRepository.updateStatus(agent);

        if(agentResult<=0){
            log.error("Could not update agent status!");
        }

        log.info("Agent_Status is update with status: {}", 300);

        return agentUpdateStatusEvent;
    }

}
