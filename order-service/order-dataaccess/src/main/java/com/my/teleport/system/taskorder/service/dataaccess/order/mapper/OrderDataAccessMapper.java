package com.my.teleport.system.taskorder.service.dataaccess.order.mapper;

import com.my.teleport.system.domain.valueobject.*;
import com.my.teleport.system.taskorder.service.dataaccess.order.data.OrderAddressConstant;
import com.my.teleport.system.taskorder.service.dataaccess.order.entity.OrderAddressEntity;
import com.my.teleport.system.taskorder.service.dataaccess.order.entity.OrderEntity;
import com.my.teleport.system.taskorder.service.dataaccess.order.entity.OrderItemEntity;
import com.my.teleport.system.order.service.domain.order.entity.Order;
import com.my.teleport.system.order.service.domain.order.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDataAccessMapper {

    public OrderEntity orderToOrderEntity(Order order) {


        List<OrderAddressEntity> address = new ArrayList<>();
        address.add(deliveryAddressToAddressEntity(order.getPickupAddress(), OrderAddressConstant.PICKUP_ADDRESS_TYPE));
        address.add(deliveryAddressToAddressEntity(order.getDeliveryAddress(),OrderAddressConstant.DELIVERY_ADDRESS_TYPE));


        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId().getValue())
                .clientId(order.getClientId().getValue())
                .orderStatus(order.getOrderStatus().getValue())
                .price(order.getPrice().getAmount())
                .trackingId(order.getTrackingId().getValue().toString())
                .items(orderItemsToOrderItemEntities(order.getItems()))
                .phoneNo(order.getPhoneNo())
                .createdDate(order.getCreatedDate())
                .processedDate(order.getProcessedDate())
                .cancelledDate(order.getCancelledDated())
                .remark(order.getRemark())
                .orderAddress(address)
                .build();


        orderEntity.getOrderAddress().forEach(orderAddressEntity -> orderAddressEntity.setOrder(orderEntity));
        orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));


        return orderEntity;
    }


    private List<OrderItemEntity> orderItemsToOrderItemEntities(List<OrderItem> items) {
        return items.stream()
                .map(orderItem -> OrderItemEntity.builder()
                        .id(orderItem.getId().getValue())
                        .itemName(orderItem.getItemName())
                        .quantity(orderItem.getQuantity())
                        .itemPrice(orderItem.getItemPrice().getAmount())
                        .build())
                .collect(Collectors.toList());
    }


    private OrderAddressEntity deliveryAddressToAddressEntity(OrderAddress deliveryAddress, Integer addressType) {
        return OrderAddressEntity.builder()
                .id(deliveryAddress.getId())
                .name(deliveryAddress.getName())
                .phoneNo(deliveryAddress.getPhoneNo())
                .email(deliveryAddress.getEmail())
                .street(deliveryAddress.getStreet())
                .postalCode(deliveryAddress.getPostalCode())
                .region(deliveryAddress.getRegion())
                .addressType(addressType)
                .township(deliveryAddress.getTownship())
                .houseNo(deliveryAddress.getHouseNo())
                .suburb(deliveryAddress.getSuburb())
                .floor(deliveryAddress.getFloor())
                .deliveryInstruction(deliveryAddress.getDeliveryInstructions())
                .label(deliveryAddress.getLabelCode())
                .build();
    }

    private OrderAddress deliveryAddressEntityToAddress(OrderAddressEntity deliveryAddressEntity) {
        return new OrderAddress(deliveryAddressEntity.getId(), deliveryAddressEntity.getName(), deliveryAddressEntity.getEmail(), deliveryAddressEntity.getPhoneNo(), deliveryAddressEntity.getStreet(), deliveryAddressEntity.getHouseNo(), deliveryAddressEntity.getSuburb(),
                deliveryAddressEntity.getFloor(), deliveryAddressEntity.getDeliveryInstruction(), deliveryAddressEntity.getPostalCode(), deliveryAddressEntity.getRegion()
        , deliveryAddressEntity.getTownship(), deliveryAddressEntity.getLabel());
    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return Order.builder().
                orderId(new OrderId(orderEntity.getId()))
                .clientId(new ClientId(orderEntity.getClientId()))
                .orderStatus(OrderStatus.fromInt(orderEntity.getOrderStatus()))
                .price(new Money(orderEntity.getPrice()))
                .phoneNo(orderEntity.getPhoneNo())
                .createdDate(orderEntity.getCreatedDate())
                .processeddDate(orderEntity.getProcessedDate())
                .items(itemEntitiesToOrderItem(orderEntity.getItems()))
                .cancelledDate(orderEntity.getCancelledDate())
                .remark(orderEntity.getRemark())
                .trackingId(new TrackingId(orderEntity.getTrackingId()))
                .pickupAddress(deliveryAddressEntityToAddress(orderEntity.getOrderAddress().stream().filter(x-> x.getAddressType().equals(OrderAddressConstant.PICKUP_ADDRESS_TYPE)).findFirst().get()))
                .deliveryAddress(deliveryAddressEntityToAddress(orderEntity.getOrderAddress().stream().filter(x-> x.getAddressType().equals(OrderAddressConstant.DELIVERY_ADDRESS_TYPE)).findFirst().get()))
                .build();

    }

    private List<OrderItem> itemEntitiesToOrderItem(List<OrderItemEntity> items) {
        return items.stream()
                .map(orderItem -> OrderItem.builder()
                        .orderItemId(new OrderItemId(orderItem.getId()))
                        .orderId(new OrderId(orderItem.getOrder().getId()))
                        .itemName(orderItem.getItemName())
                        .quantity(orderItem.getQuantity())
                        .itemPrice(new Money(orderItem.getItemPrice()))
                        .build()   )
                .collect(Collectors.toList());
    }



}
