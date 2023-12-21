package com.my.teleport.system.agent.service.dataaccess.agent.adapter;

import com.my.teleport.system.agent.service.dataaccess.agent.entity.AgentEntity;
import com.my.teleport.system.agent.service.dataaccess.agent.mapper.AgentDataAccessMapper;
import com.my.teleport.system.agent.service.dataaccess.agent.repository.AgentJpaRepository;
import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.agent.service.domain.exception.AgentNotFoundException;
import com.my.teleport.system.agent.service.domain.ports.output.repository.AgentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Component
public class AgentRepositoryImpl implements AgentRepository {

    private final AgentJpaRepository agentJpaRepository;
    private final AgentDataAccessMapper agentDataAccessMapper;

    public AgentRepositoryImpl(AgentJpaRepository agentJpaRepository,
                               AgentDataAccessMapper agentDataAccessMapper) {
        this.agentJpaRepository = agentJpaRepository;
        this.agentDataAccessMapper = agentDataAccessMapper;
    }


    @Override
    public Agent save(Agent agent) {

    return agentDataAccessMapper.agentEntityToAgent(
            agentJpaRepository.save(agentDataAccessMapper.agentToAgentEntity(agent)));

    }

    @Override
    public int updateStatus(Agent agent) {
        int updateResult = agentJpaRepository.updateAgentStatus(agent.getId().getValue(),300);
        return updateResult;
    }

    @Override
    public Agent findAgentWithNotFoundDetection(final String id) {
        return this.findOneWithNotFoundDetection(id) ;
    }

    @Transactional(readOnly=true)
    public Agent findOneWithNotFoundDetection(final String agentId) {
        final Optional<Agent> agent = this.agentJpaRepository.findById(agentId).map(agentDataAccessMapper::agentEntityToAgent);
        if (agent.isEmpty()) { throw new AgentNotFoundException(agentId); }
        return agent.get();
    }

    @Override
    public Optional<Agent> findById(String agentId) {
        Optional<Agent> agentResult = agentJpaRepository.
                findById(agentId).map(agentDataAccessMapper::agentEntityToAgent);
        return agentResult;
   }



}
