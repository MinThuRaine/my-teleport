package com.my.teleport.system.client.service.dataaccess.client.mapper;

import com.my.teleport.system.client.service.dataaccess.client.entity.ClientAddressEntity;
import com.my.teleport.system.client.service.dataaccess.client.entity.ClientEntity;
import com.my.teleport.system.client.service.domain.entity.Client;
import com.my.teleport.system.domain.valueobject.Address;
import com.my.teleport.system.domain.valueobject.ClientId;
import com.my.teleport.system.domain.valueobject.ClientStatus;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientDataAccessMapper {

    public ClientEntity clientToClientEntity(Client client) {
        ClientEntity clientEntity = ClientEntity.builder()
                .id(client.getId().getValue())
                .age(client.getAge())
                .name(client.getName())
                .clientstatus(client.getClientStatus().getValue())
                .identitynumber(client.getIdentityNumber())
                .phoneno(client.getPhoneNo())
                .email(client.getEmail())
                .address(clientAddressToClientAddressEntity(client.getAddress()))
                .build();

        clientEntity.getAddress().forEach(x->x.setClient(clientEntity));

        return clientEntity;
    }

    public Client clientEntityToClient(ClientEntity clientEntity) {
        return Client.builder()
                .clientId(new ClientId(clientEntity.getId()))
                .age(clientEntity.getAge())
                .name(clientEntity.getName())
                .clientStatus(ClientStatus.ACTIVE)
                .identityNumber(clientEntity.getIdentitynumber())
                .phoneNo(clientEntity.getPhoneno())
                .build();
    }

    private List<ClientAddressEntity> clientAddressToClientAddressEntity(List<Address>  addresses){

        List<ClientAddressEntity> clientAddressEntitiesList = new ArrayList<>();

        for (Address address: addresses){
            clientAddressEntitiesList.add(ClientAddressEntity.builder().id(address.getId()).street(address.getStreet())
                    .postalCode(address.getPostalCode())
                    .region(address.getRegion()).township(address.getTownship()).houseNo(address.getHouseNo())
                    .suburb(address.getSuburb())
                    .floor(address.getFloor()).deliveryInstructions(address.getDeliveryInstructions())
                    .build());


        }

        return  clientAddressEntitiesList;
    }







}
