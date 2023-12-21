package com.my.teleport.system.agent.service.domain.dto.create;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateAgentCommand {

    private  final String name;

    private final Integer age;

    private final String identityNumber;

    private final Integer phoneNo;

}
