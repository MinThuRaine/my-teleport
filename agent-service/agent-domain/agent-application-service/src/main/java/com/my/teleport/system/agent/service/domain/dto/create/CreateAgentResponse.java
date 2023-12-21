package com.my.teleport.system.agent.service.domain.dto.create;

import com.my.teleport.system.domain.valueobject.AgentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class CreateAgentResponse {

    private final String agentId;

    private final AgentStatus agentStatus;

    private final String message;

}
