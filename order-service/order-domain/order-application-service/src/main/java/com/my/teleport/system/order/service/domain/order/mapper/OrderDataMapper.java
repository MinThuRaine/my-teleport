package com.my.teleport.system.order.service.domain.order.mapper;


import com.my.teleport.system.domain.valueobject.ClientId;
import com.my.teleport.system.domain.valueobject.Money;
import com.my.teleport.system.domain.valueobject.OrderAddress;
import com.my.teleport.system.order.service.domain.order.dto.create.*;
import com.my.teleport.system.order.service.domain.order.dto.create.OrderItem;
import com.my.teleport.system.order.service.domain.order.entity.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .clientId(new ClientId(createOrderCommand.getClientId()))
                .price(new Money(new BigDecimal("0")))
                .pickupAddress(orderAddressToAddressEntity(createOrderCommand.getPickupAddress()))
                .deliveryAddress(orderAddressToAddressEntity(createOrderCommand.getDeliveryAddress()))
                .phoneNo(createOrderCommand.getPhoneNo()).remark(createOrderCommand.getRemark())
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    private List<com.my.teleport.system.order.service.domain.order.entity.OrderItem> orderItemsToOrderItemEntities(
            List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        com.my.teleport.system.order.service.domain.order.entity.OrderItem.builder()
                                .itemName(orderItem.getItemName())
                                .quantity(orderItem.getQuantity())
                                .itemPrice(new Money(orderItem.getItemPrice()))
                                .build()).collect(Collectors.toList());
    }

    private OrderAddress orderAddressToAddressEntity(OrderAddressDto orderAddress) {
        return new OrderAddress(
                UUID.randomUUID().toString(),
                orderAddress.getName(),
                orderAddress.getEmail(),
                orderAddress.getPhoneNo(),
                orderAddress.getStreet(),
                orderAddress.getHouseNo(),
                orderAddress.getSuburb(),
                orderAddress.getFloor(),
                orderAddress.getDeliveryInstructions(),
                orderAddress.getPostalCode(),
                orderAddress.getRegion(),
                orderAddress.getTownship(),
                orderAddress.getLabel()
        );
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus().toString())
                .message(message)
                .build();
    }

    public OrderProcessingRespond orderToProcessOrderResponse(Order order, String message) {
        return OrderProcessingRespond.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus().toString())
                .message(message)
                .build();
    }

    public OrderCancelRespond orderToCancelOrderResponse(Order order, String message) {
        return OrderCancelRespond.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus().toString())
                .message(message)
                .build();
    }




}
