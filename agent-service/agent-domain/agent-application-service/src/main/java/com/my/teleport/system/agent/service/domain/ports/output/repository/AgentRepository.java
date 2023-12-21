package com.my.teleport.system.agent.service.domain.ports.output.repository;

import com.my.teleport.system.agent.service.domain.entity.Agent;

import java.util.Optional;

public interface AgentRepository {

    Agent save(Agent agent);

    int updateStatus(Agent agent);

    Optional<Agent> findById(String agentId);

    Agent findAgentWithNotFoundDetection(String agentId);



}
