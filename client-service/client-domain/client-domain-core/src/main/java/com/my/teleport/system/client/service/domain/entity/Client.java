package com.my.teleport.system.client.service.domain.entity;


import com.my.teleport.system.domain.entity.AggregateRoot;
import com.my.teleport.system.domain.valueobject.Address;
import com.my.teleport.system.domain.valueobject.ClientId;
import com.my.teleport.system.domain.valueobject.ClientStatus;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Client extends AggregateRoot<ClientId> {


    private  final String name;

    private final Integer age;

    private ClientStatus clientStatus;

    private final String identityNumber;

    private final Integer phoneNo;

    private final String email;

    private  final  List<Address>  address;

    private Client(Builder builder) {
        name = builder.name;
        age = builder.age;
        clientStatus = builder.clientStatus;
        identityNumber = builder.identityNumber;
        phoneNo = builder.phoneNo;
        address = builder.address;
        email = builder.email;
        super.setId(builder.clientId);
    }


    public  void initializeClient(){
        setId(new ClientId(UUID.randomUUID().toString()));
        this.clientStatus = ClientStatus.PENDING;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private Integer age;
        private ClientStatus clientStatus;
        private String identityNumber;
        private Integer phoneNo;
        private ClientId clientId;
        private String email;
        private List<Address> address;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder age(Integer val) {
            age = val;
            return this;
        }

        public Builder address(List<Address> val) {
            address = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder clientStatus(ClientStatus val) {
            clientStatus = val;
            return this;
        }

        public Builder identityNumber(String val) {
            identityNumber = val;
            return this;
        }

        public Builder phoneNo(Integer val) {
            phoneNo = val;
            return this;
        }

        public Builder clientId(ClientId val) {
            clientId = val;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
