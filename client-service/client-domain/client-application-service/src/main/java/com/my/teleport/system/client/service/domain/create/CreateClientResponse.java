package com.my.teleport.system.client.service.domain.create;

import com.my.teleport.system.domain.valueobject.AgentStatus;
import com.my.teleport.system.domain.valueobject.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class CreateClientResponse {

    private final String clientId;

    private final ClientStatus clientStatus;

    private final String message;

}
