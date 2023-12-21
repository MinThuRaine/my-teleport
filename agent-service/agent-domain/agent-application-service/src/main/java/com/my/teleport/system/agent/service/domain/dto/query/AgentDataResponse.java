package com.my.teleport.system.agent.service.domain.dto.query;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AgentDataResponse {

    public String name;

    public Integer age;

    public String identityNumber;

    public Integer phoneNo;

}
