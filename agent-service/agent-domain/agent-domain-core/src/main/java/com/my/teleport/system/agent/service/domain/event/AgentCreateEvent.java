package com.my.teleport.system.agent.service.domain.event;

import com.my.teleport.system.agent.service.domain.entity.Agent;

import java.time.ZonedDateTime;

public class AgentCreateEvent extends  AgentEvent {

    public  AgentCreateEvent(Agent agent, ZonedDateTime createdDate){
        super(agent, createdDate);
    }
}
