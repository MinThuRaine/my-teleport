package com.my.teleport.system.order.service.domain.order.entity;

import com.my.teleport.system.domain.valueobject.OrderStatus;
import com.my.teleport.system.order.service.domain.order.event.OrderEvent;

public interface OrderLifeCycleStateMachine {
    OrderStatus transition(OrderEvent loanEvent, OrderStatus from);

}
