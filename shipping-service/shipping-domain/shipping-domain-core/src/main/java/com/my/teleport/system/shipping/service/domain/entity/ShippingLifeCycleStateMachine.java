package com.my.teleport.system.shipping.service.domain.entity;

import com.my.teleport.system.domain.valueobject.ShippingStatus;
import com.my.teleport.system.shipping.service.domain.event.ShippingEvent;

public interface ShippingLifeCycleStateMachine {
    ShippingStatus transition(ShippingEvent shippingEvent, ShippingStatus from);

}
