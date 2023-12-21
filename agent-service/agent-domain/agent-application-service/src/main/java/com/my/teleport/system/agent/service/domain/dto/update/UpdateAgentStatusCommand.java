package com.my.teleport.system.agent.service.domain.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateAgentStatusCommand {

    private  final String agentId;

    private final Integer status;

}
