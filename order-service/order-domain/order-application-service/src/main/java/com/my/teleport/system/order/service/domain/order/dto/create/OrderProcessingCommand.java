package com.my.teleport.system.order.service.domain.order.dto.create;

import lombok.*;


@Getter
@Builder
@AllArgsConstructor
public class OrderProcessingCommand {

    private final String orderId;
    private final String remark;

}
