package com.my.teleport.system.order.service.domain.order.dto.create;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {


    private  final String clientId;

    private final OrderAddressDto pickupAddress;

    private final OrderAddressDto deliveryAddress;

    private final Integer phoneNo;

    private final String remark;

    private final List<OrderItem> items;



}
