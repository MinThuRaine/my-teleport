package com.my.teleport.system.agent.service.domain.event;

import com.my.teleport.system.agent.service.domain.entity.Agent;

import java.time.ZonedDateTime;

public class AgentUpdateStatusEvent extends  AgentEvent {

    public  AgentUpdateStatusEvent(Agent agent, ZonedDateTime createdDate){
        super(agent, createdDate);
    }


}
