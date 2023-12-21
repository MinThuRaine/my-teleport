package com.my.teleport.system.client.service.dataaccess.client.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_address")
@Entity
public class ClientAddressEntity {
    @Id
    private String id;

    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private ClientEntity client;

    @Column(name = "street")
    private  String street;

    @Column(name = "postalcode")
    private  String postalCode;

    @Column(name = "region")
    private  Integer region;


    @Column(name = "township")
    private  Integer township;

    @Column(name = "houseno")
    private String houseNo;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "floor")
    private String floor;

    @Column(name = "deliveryinstructions")
    private String deliveryInstructions;

}
