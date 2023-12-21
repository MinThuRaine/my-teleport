package com.my.teleport.system.client.service.domain.create;


import com.my.teleport.system.domain.valueobject.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CreateClientCommand {

    private  final String name;

    private final Integer age;

    private final String identityNumber;

    private final Integer phoneNo;

    private final String email;

    private final List<ClientAddressDto> address;

}
