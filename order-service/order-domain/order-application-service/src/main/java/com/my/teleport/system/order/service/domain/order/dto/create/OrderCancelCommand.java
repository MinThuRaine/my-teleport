package com.my.teleport.system.order.service.domain.order.dto.create;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderCancelCommand {

    private  final String orderId;

    private  final String remark;



}
