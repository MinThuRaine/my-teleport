package com.my.teleport.system.agent.service.dataaccess.agent.mapper;

import com.my.teleport.system.agent.service.dataaccess.agent.entity.AgentEntity;
import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.domain.valueobject.AgentId;
import com.my.teleport.system.domain.valueobject.AgentStatus;
import org.springframework.stereotype.Component;

@Component
public class AgentDataAccessMapper {

    public AgentEntity agentToAgentEntity(Agent agent) {
        AgentEntity agentEntity = AgentEntity.builder()
                .id(agent.getId().getValue().toString())
                .age(agent.getAge())
                .name(agent.getName())
                .status(agent.getStatus().getValue())
                .identitynumber(agent.getIdentityNumber())
                .phoneno(agent.getPhoneNo())
                        .build();

        return agentEntity;
    }

    public  Agent agentEntityToAgent(AgentEntity agentEntity){
        return  Agent.builder()
                .agentId(new AgentId(agentEntity.getId()))
                .age(agentEntity.getAge())
                .name(agentEntity.getName())
                .agentStatus(AgentStatus.ACTIVE)
                .identityNumber(agentEntity.getIdentitynumber())
                .phoneNo(agentEntity.getPhoneno())
                .build();
    }

}
