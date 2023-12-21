package com.my.teleport.system.taskorder.service.dataaccess.order.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_address")
@Entity
public class OrderAddressEntity {
    @Id
    private String id;

    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private OrderEntity order;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneno")
    private String phoneNo;

    @Column(name = "email")
    private String email;

    @Column(name = "street")
    private  String street;

    @Column(name = "postalcode")
    private  String postalCode;

    @Column(name = "region")
    private  Integer region;

    @Column(name = "addresstype")
    private  Integer addressType;

    @Column(name = "township")
    private  Integer township;


    @Column(name = "houseno")
    private String houseNo;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "floor")
    private String floor;

    @Column(name = "deliveryinstruction")
    private String deliveryInstruction;

    @Column(name = " label ")
    private String label;


    @Override
    public boolean equals(Object o) {
        if (this != o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderAddressEntity that = (OrderAddressEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
