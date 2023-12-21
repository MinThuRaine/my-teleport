package com.my.teleport.system.order.service.domain.order.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderAddressDto {


    private final String name;

    private final String phoneNo;

    private final String email;
    private final String street;

    private final String postalCode;

    private final Integer region;

    private final Integer township;

    private final String houseNo;

    private final String suburb;

    private final String floor;

    private final String deliveryInstructions;

    private final String label;


}
