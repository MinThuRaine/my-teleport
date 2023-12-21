package com.my.teleport.system.client.service.domain.mapper;


import com.my.teleport.system.client.service.domain.create.ClientAddressDto;
import com.my.teleport.system.client.service.domain.create.CreateClientCommand;
import com.my.teleport.system.client.service.domain.create.CreateClientResponse;
import com.my.teleport.system.client.service.domain.entity.Client;
import com.my.teleport.system.domain.valueobject.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ClientDataMapper {

    public Client createClientCommandToClient(CreateClientCommand createClientCommand) {
        return Client.builder().name(createClientCommand.getName())
                .identityNumber(createClientCommand.getIdentityNumber())
                .age(createClientCommand.getAge()).phoneNo(createClientCommand.getPhoneNo())
                .email(createClientCommand.getEmail())
                .address(clientAddressToAddressEntity(createClientCommand.getAddress())).build();
    }


    public CreateClientResponse clientToCreateClientResponse(Client client, String message) {
        return CreateClientResponse.builder().clientId(client.getId().getValue())
                .clientStatus(client.getClientStatus()).message(message).build();
    }

    private List<Address> clientAddressToAddressEntity(List<ClientAddressDto> clientAddressDto) {

        List<Address> clientAddressList = new ArrayList<>();

        for (ClientAddressDto clientAddressDtoData : clientAddressDto) {
            Address address = new Address(
                    UUID.randomUUID().toString(),
                    clientAddressDtoData.getStreet(),
                    clientAddressDtoData.getHouseNo(),
                    clientAddressDtoData.getSuburb(),
                    clientAddressDtoData.getFloor(),
                    clientAddressDtoData.getDeliveryInstructions(),
                    clientAddressDtoData.getPostalCode(),
                    clientAddressDtoData.getRegion(),
                    clientAddressDtoData.getTownship(),
                    clientAddressDtoData.getLabelCode()
            );
            clientAddressList.add(address);
        }

        return  clientAddressList;
    }



}
