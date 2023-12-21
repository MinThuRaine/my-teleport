package com.my.teleport.system.shipping.service.domain.entity;

import com.my.teleport.system.domain.entity.AggregateRoot;
import com.my.teleport.system.domain.valueobject.*;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ShippingTransaction extends AggregateRoot<ShippingTransactionId> {


  private ShippingId shippingId;
  private AgentId agentId;
  private ZonedDateTime agentAssignDate;
  private ShippingType shippingType;
  private ShippingTransactionStatus shippingTransactionStatus;
  private String deliveryInstruction;
  private ZonedDateTime createdDate;
  private OrderAddress address;

  private ShippingTransaction(Builder builder) {
    shippingId = builder.shippingId;
    agentId = builder.agentId;
    agentAssignDate = builder.agentAssignDate;
    shippingType = builder.shippingType;
    shippingTransactionStatus = builder.shippingTransactionStatus;
    deliveryInstruction = builder.deliveryInstruction;
    createdDate = builder.createdDate;
    address = builder.address;
  }

  public static Builder builder() {
    return new Builder();
  }

  public  void initializeShippingTransaction(){
    setId(new ShippingTransactionId(UUID.randomUUID().toString()));
  }


  public static final class Builder {
    private ShippingId shippingId;
    private AgentId agentId;
    private ZonedDateTime agentAssignDate;
    private ShippingType shippingType;
    private ShippingTransactionStatus shippingTransactionStatus;
    private String deliveryInstruction;
    private ZonedDateTime createdDate;
    private OrderAddress address;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder shippingId(ShippingId val) {
      shippingId = val;
      return this;
    }

    public Builder agentId(AgentId val) {
      agentId = val;
      return this;
    }

    public Builder agentAssignDate(ZonedDateTime val) {
      agentAssignDate = val;
      return this;
    }

    public Builder shippingType(ShippingType val) {
      shippingType = val;
      return this;
    }

    public Builder shippingTransactionStatus(ShippingTransactionStatus val) {
      shippingTransactionStatus = val;
      return this;
    }

    public Builder deliveryInstruction(String val) {
      deliveryInstruction = val;
      return this;
    }

    public Builder createdDate(ZonedDateTime val) {
      createdDate = val;
      return this;
    }

    public Builder address(OrderAddress val) {
      address = val;
      return this;
    }

    public ShippingTransaction build() {
      return new ShippingTransaction(this);
    }
  }
}
