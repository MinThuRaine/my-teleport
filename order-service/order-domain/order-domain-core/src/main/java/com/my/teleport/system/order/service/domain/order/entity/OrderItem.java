package com.my.teleport.system.order.service.domain.order.entity;

import com.my.teleport.system.domain.entity.BaseEntity;
import com.my.teleport.system.domain.valueobject.Money;
import com.my.teleport.system.domain.valueobject.OrderId;
import com.my.teleport.system.domain.valueobject.OrderItemId;
import lombok.Getter;


@Getter
public class OrderItem extends BaseEntity<OrderItemId> {

    private final String itemName;
    private final int quantity;
    private OrderId orderId;
    private final Money itemPrice;

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId){
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        itemName = builder.itemName;
        quantity = builder.quantity;
        itemPrice = builder.itemPrice;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private OrderItemId orderItemId;
        private String itemName;
        private int quantity;
        private OrderId orderId;
        private Money itemPrice;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder itemName(String val) {
            itemName = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder itemPrice(Money val) {
            itemPrice = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
