package com.my.teleport.system.order.service.domain.order.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class CreateOrderResponse {

    private final String orderTrackingId;

    private final String orderStatus;

    private final String message;

}
