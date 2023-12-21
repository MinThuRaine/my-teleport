package com.my.teleport.system.agent.service.domain;

import com.my.teleport.system.agent.service.domain.dto.query.AgentDataQuery;
import com.my.teleport.system.agent.service.domain.dto.query.AgentDataResponse;
import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.agent.service.domain.exception.AgentNotFoundException;
import com.my.teleport.system.agent.service.domain.mapper.AgentDataMapper;
import com.my.teleport.system.agent.service.domain.ports.input.AgentReadApplicationService;
import com.my.teleport.system.agent.service.domain.ports.output.repository.AgentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AgentReadApplicationServiceImpl implements AgentReadApplicationService {

    private final AgentRepository agentRepository;
    private final AgentDataMapper agentDataMapper;

    public AgentReadApplicationServiceImpl(AgentRepository agentRepository, AgentDataMapper agentDataMapper) {
        this.agentDataMapper = agentDataMapper;
        this.agentRepository = agentRepository;
    }

    @Override
    public AgentDataResponse getAgentByID(AgentDataQuery agentDataQuery) {

        Optional<Agent> agentResult =
                agentRepository.findById(agentDataQuery.getAgentId());
        if (agentResult.isEmpty()) {
            log.warn("Could not find agent with agent id: {}", agentDataQuery.getAgentId());
            throw new AgentNotFoundException() {};
        }
        return agentDataMapper.agentToAgentDataResponse(agentResult.get());

    }


}
