package com.my.teleport.system.order.service.domain.order.entity;

import com.my.teleport.system.domain.valueobject.OrderStatus;
import com.my.teleport.system.order.service.domain.order.event.OrderEvent;

import java.util.List;

public class ServiceAgentOrderLifecycleStateMachine implements  OrderLifeCycleStateMachine {

    private final List<OrderStatus> allowedOrderStatuses;

    public ServiceAgentOrderLifecycleStateMachine(final List<OrderStatus> allowedOrderStatuses) {
        this.allowedOrderStatuses = allowedOrderStatuses;
    }

    @Override
    public OrderStatus transition(OrderEvent orderEvent, OrderStatus from) {

      OrderStatus newState = from;

      switch (orderEvent){
          case ORDER_CREATED:
              if (from == null) {
                  newState = stateOf(OrderStatus.PENDING, this.allowedOrderStatuses);
              }
              break;
          case ORDER_PROCESS:
              if (from.hasStateOf(OrderStatus.PENDING)) {
                  newState = stateOf(OrderStatus.PROCESSING, this.allowedOrderStatuses);
              }
              break;
          case ORDER_SHIPPING:
              if (from.hasStateOf(OrderStatus.PROCESSING)) {
                  newState = stateOf(OrderStatus.SHIPPING, this.allowedOrderStatuses);
              }
              break;
          case ORDER_COMPLETED:
              if (from.hasStateOf(OrderStatus.SHIPPING)) {
                  newState = stateOf(OrderStatus.COMPLETED, this.allowedOrderStatuses);
              }
              break;
          case ORDER_CANCELLED:
              if (anyOfAllowedWhenComingFrom(from,OrderStatus.PROCESSING, OrderStatus.SHIPPING, OrderStatus.COMPLETED )) {
                  newState = stateOf(OrderStatus.CANCEL, this.allowedOrderStatuses);
              }
              break;
      }

        return newState;
    }


    private OrderStatus stateOf(final OrderStatus state, final List<OrderStatus> allowedOrderStatuses) {
        OrderStatus match = null;
        for (final OrderStatus orderStatus : allowedOrderStatuses) {
            if (orderStatus.hasStateOf(state)) {
                match = orderStatus;
                break;
            }
        }
        return match;
    }

    private boolean anyOfAllowedWhenComingFrom(final OrderStatus state, final OrderStatus... allowedStates) {
        boolean allowed = false;

        for (final OrderStatus allowedState : allowedStates) {
            if (state.hasStateOf(allowedState)) {
                allowed = true;
                break;
            }
        }

        return allowed;
    }

}
