package com.my.teleport.system.shipping.service.domain.ports.input;

import com.my.teleport.system.shipping.service.domain.entity.Shipping;
import com.my.teleport.system.shipping.service.domain.event.ShippingEvent;

public interface ShippingApplicationService {

    ShippingEvent validateAndInitiateShipping(Shipping shipping);


}
