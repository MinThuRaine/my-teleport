package com.my.teleport.system.agent.service.domain.dto.update;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class UpdateAgentStatusResponse {

    private final String agentId;

    private final String message;

}
