package com.my.teleport.system.shipping.service.domain.entity;

import com.my.teleport.system.domain.data.ApiParameterError;
import com.my.teleport.system.domain.entity.AggregateRoot;
import com.my.teleport.system.domain.exception.PlatformApiDataValidationException;
import com.my.teleport.system.domain.valueobject.*;
import com.my.teleport.system.shipping.service.domain.event.ShippingEvent;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Shipping extends AggregateRoot<ShippingId> {


    private ShippingStatus shippingStatus;
    private ShippingType shippingType;
    private OrderId orderId;
    private ClientId clientId;
    private Integer totalItem;
    private ZonedDateTime createdDate;
    private ZonedDateTime expectedDeliveredDate;
    private List<ShippingTransaction> shippingTransaction;
    private List<ShippingAgentAssignHistory> shippingAssignHistories;

    private Shipping(Builder builder) {
        shippingStatus = builder.shippingStatus;
        shippingType = builder.shippingType;
        orderId = builder.orderId;
        clientId = builder.clientId;
        totalItem = builder.totalItem;
        createdDate = builder.createdDate;
        expectedDeliveredDate = builder.expectedDeliveredDate;
        shippingTransaction = builder.shippingTransaction;
    }


    public  void initializeShipping(){

        setId(new ShippingId(UUID.randomUUID().toString()));

        validateShippingStatus(ShippingEvent.SHIPPING_CREATE);

    }


    public void validateShippingStatus(final ShippingEvent event) {

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();

        ApiParameterError error;
        String defaultUserMessage = "";

        switch (event) {
            case SHIPPING_CREATE:
                break;
            case SHIPPING_SCHEDULE:
                if (!isCreatedAndPending()) {
                    defaultUserMessage = "Shipping Process is not allowed. Shipping is not in created and pending state.";
                    error = ApiParameterError.generalError(
                            "error.msg.shipping.process.is.not.created.and.pending.state", defaultUserMessage);
                    dataValidationErrors.add(error);
                }
                break;

        }

        if (!dataValidationErrors.isEmpty()) {
            throw new PlatformApiDataValidationException(dataValidationErrors);
        }

    }

    public ShippingStatus status() {
        return ShippingStatus.fromInt(this.shippingStatus.getValue());
    }

    public boolean isCreatedAndPending() {
        return status().isPending();
    }

    public static Builder builder() {
        return new Builder();
    }



    public static final class Builder {
        private ShippingStatus shippingStatus;
        private ShippingType shippingType;
        private OrderId orderId;
        private ClientId clientId;
        private Integer totalItem;
        private ZonedDateTime createdDate;
        private ZonedDateTime expectedDeliveredDate;
        private List<ShippingTransaction> shippingTransaction;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder shippingStatus(ShippingStatus val) {
            shippingStatus = val;
            return this;
        }

        public Builder shippingType(ShippingType val) {
            shippingType = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder clientId(ClientId val) {
            clientId = val;
            return this;
        }

        public Builder totalItem(Integer val) {
            totalItem = val;
            return this;
        }

        public Builder createdDate(ZonedDateTime val) {
            createdDate = val;
            return this;
        }

        public Builder expectedDeliveredDate(ZonedDateTime val) {
            expectedDeliveredDate = val;
            return this;
        }

        public Builder shippingTransaction(List<ShippingTransaction> val) {
            shippingTransaction = val;
            return this;
        }

        public Shipping build() {
            return new Shipping(this);
        }
    }
}