package com.my.teleport.system.order.service.domain.order.entity;

import com.my.teleport.system.domain.data.ApiParameterError;
import com.my.teleport.system.domain.entity.AggregateRoot;
import com.my.teleport.system.domain.exception.GeneralPlatformDomainRuleException;
import com.my.teleport.system.domain.exception.PlatformApiDataValidationException;
import com.my.teleport.system.domain.valueobject.*;
import com.my.teleport.system.order.service.domain.order.event.OrderEvent;
import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.my.teleport.system.domain.DomainConstants.UTC;

@Getter
public class Order extends AggregateRoot<OrderId> {


    private final ClientId clientId;
    private final OrderAddress pickupAddress;
    private final OrderAddress deliveryAddress;
    private final Integer phoneNo;
    private final String remark;
    private final List<OrderItem> items;
    private final Money price;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private ZonedDateTime createdDate;
    private ZonedDateTime cancelledDated;
    private ZonedDateTime processedDate;


    private OrderLifeCycleStateMachine orderLifeCycleStateMachine;


    private Order(Builder builder) {
        super.setId(builder.orderId);
        clientId = builder.clientId;
        pickupAddress = builder.pickupAddress;
        deliveryAddress = builder.deliveryAddress;
        items = builder.items;
        remark = builder.remark;
        price = builder.price;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        phoneNo = builder.phoneNo;
        createdDate = builder.createdDate;
        processedDate = builder.processedDate;
        cancelledDated = builder.cancelledDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID().toString()));
        trackingId = new TrackingId(UUID.randomUUID().toString());

        validateOrderStatus(OrderEvent.ORDER_CREATED);

        createdDate = ZonedDateTime.now(ZoneId.of(UTC));

        orderStatus = orderLifeCycleStateMachine.transition(OrderEvent.ORDER_CREATED, null);

        initializeOrderItems();
    }

    public void orderCancelled() {

        validateOrderStatus(OrderEvent.ORDER_CANCELLED);

        cancelledDated = ZonedDateTime.now(ZoneId.of(UTC));

        orderStatus = orderLifeCycleStateMachine.transition(OrderEvent.ORDER_CANCELLED, OrderStatus.fromInt(this.orderStatus.getValue()));

    }

    public void orderProcessing() {

        validateOrderStatus(OrderEvent.ORDER_PROCESS);

        processedDate = ZonedDateTime.now(ZoneId.of(UTC));

        orderStatus = orderLifeCycleStateMachine.transition(OrderEvent.ORDER_PROCESS, OrderStatus.fromInt(this.orderStatus.getValue()));


    }

    public void validateOrderStatus(final OrderEvent event) {

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();

        ApiParameterError error;
        String defaultUserMessage = "";

        switch (event) {
            case ORDER_CREATED:
                break;
            case ORDER_PROCESS:
                if (!isCreatedAndPending()) {
                    defaultUserMessage = "Order Process is not allowed. Order is not in created and pending state.";
                    error = ApiParameterError.generalError(
                            "error.msg.order.process.is.not.created.and.pending.state", defaultUserMessage);
                    dataValidationErrors.add(error);
                }
                break;
            case ORDER_SHIPPING:
                if (!isShipping()) {
                    defaultUserMessage = "Order Shipping is not allowed. Order is not in Process state.";
                    error = ApiParameterError.generalError(
                            "error.msg.order.schedule.is.not.in.confirm.state", defaultUserMessage);
                    dataValidationErrors.add(error);
                }
                break;
            case ORDER_COMPLETED:
                if (!isCreatedAndPending()) {
                    defaultUserMessage = "Order COMPLETED is not allowed. Order is not in created and pending state.";
                    error = ApiParameterError.generalError(
                            "error.msg.order.reject.is.not.created.and.pending.state", defaultUserMessage);
                    dataValidationErrors.add(error);
                }
                break;
            case ORDER_CANCELLED:
                if (!isCreatedAndPending()) {
                    defaultUserMessage = "Order cancel is not allowed. Order is not in created and processing state.";
                    error = ApiParameterError.generalError(
                            "error.msg.order.reject.is.not.created.and.processing.state", defaultUserMessage);
                    dataValidationErrors.add(error);
                }
                break;

        }

        if (!dataValidationErrors.isEmpty()) {
            throw new PlatformApiDataValidationException(dataValidationErrors);
        }

    }

    public OrderStatus status() {
        return OrderStatus.fromInt(this.orderStatus.getValue());
    }

    public boolean isShipping() {
        return status().isShipping();
    }

    public boolean isCreatedAndPending() {
        return status().isPending();
    }

    public boolean isStatusAlreadyChanges(OrderStatus status) {
        Integer a = status().getValue();
        Integer b = status.getValue();

        return status().getValue().equals(status.getValue());
    }

    public void setHelper(OrderLifeCycleStateMachine orderLifeCycleStateMachine) {
        this.orderLifeCycleStateMachine = orderLifeCycleStateMachine;
    }

    private void initializeOrderItems() {

        if (items.size() <= 0) {
            throw new GeneralPlatformDomainRuleException("error.msg.order.order.item.not.found", "Order item not found.");
        }

        long itemId = 1;
        for (OrderItem orderItem : items) {
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }

    public static final class Builder {
        private OrderId orderId;
        private ClientId clientId;
        private OrderAddress pickupAddress;
        private OrderAddress deliveryAddress;
        private Money price;
        private TrackingId trackingId;
        private String remark;
        private List<OrderItem> items;
        private OrderStatus orderStatus;
        private Integer phoneNo;

        private ZonedDateTime createdDate;

        private ZonedDateTime processedDate;

        private ZonedDateTime cancelledDate;


        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder clientId(ClientId val) {
            clientId = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder pickupAddress(OrderAddress val) {
            pickupAddress = val;
            return this;
        }

        public Builder deliveryAddress(OrderAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder phoneNo(Integer val) {
            phoneNo = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder createdDate(ZonedDateTime val) {
            createdDate = val;
            return this;
        }

        public Builder processeddDate(ZonedDateTime val) {
            processedDate = val;
            return this;
        }

        public Builder cancelledDate(ZonedDateTime val) {
            cancelledDate = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
