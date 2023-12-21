package com.my.teleport.system.agent.service.domain.exception;

import com.my.teleport.system.domain.exception.AbstractPlatformResourceNotFoundException;

public class AgentNotFoundException extends AbstractPlatformResourceNotFoundException {

    public AgentNotFoundException( final String id){
        super("error.msg.agent.id.invalid", "Agent with identifier " + id + " does not exist", id);
    }

    public AgentNotFoundException() {
        super("error.msg.agent.not.found.with.basic.details", "Agent not found with basic details.");
    }
}
