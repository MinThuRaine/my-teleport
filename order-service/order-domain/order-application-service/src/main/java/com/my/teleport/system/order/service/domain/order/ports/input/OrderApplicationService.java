package com.my.teleport.system.order.service.domain.order.ports.input;

import com.my.teleport.system.order.service.domain.order.dto.create.*;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand);

    OrderProcessingRespond processOrder(OrderProcessingCommand processOrderCommand);

    OrderCancelRespond cancelOrder(OrderCancelCommand orderCancelCommand);


}
