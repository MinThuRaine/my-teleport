package com.my.teleport.system.order.service.domain.order.dto.create;

import com.my.teleport.system.domain.valueobject.Money;
import com.my.teleport.system.domain.valueobject.OrderId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    private final String itemName;
    private final Integer quantity;
    private final BigDecimal itemPrice;

}
