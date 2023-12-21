package com.my.teleport.system.agent.service.domain.event;

import com.my.teleport.system.agent.service.domain.entity.Agent;
import com.my.teleport.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public abstract class AgentEvent implements DomainEvent<Agent> {

    private final Agent agent;

    private final ZonedDateTime createdAt;

    public AgentEvent(Agent agent, ZonedDateTime createdAt){
        this.agent = agent;
        this.createdAt = createdAt;
    }

    public Agent getAgent() {
        return agent;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
