package com.my.teleport.system.domain.valueobject;

public enum OrderStatus {

    PENDING(100, "orderStatusType.pending"),
    PROCESSING(200, "orderStatusType.processing"),
    SHIPPING(300, "orderStatusType.shipping"),
    COMPLETED( 500, "orderStatusType.completed"),
    CANCEL(800, "orderStatusStatusType.started");
    private final Integer value;
    private final String code;

    OrderStatus(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public static OrderStatus fromInt(final Integer statusValue) {

        OrderStatus enumeration = OrderStatus.PENDING;
        switch (statusValue) {
            case 100:
                enumeration = OrderStatus.PENDING;
                break;
            case 200:
                enumeration = OrderStatus.PROCESSING;
                break;
            case 300:
                enumeration = OrderStatus.SHIPPING;
                break;
            case 400:
                enumeration = OrderStatus.COMPLETED;
                break;
            case 500:
                enumeration = OrderStatus.CANCEL;
                break;
        }
        return enumeration;
    }

    public boolean hasStateOf(final OrderStatus state) {
        return this.value.equals(state.getValue());
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isPending() {
        return this.value.equals(OrderStatus.PENDING.getValue());
    }

    public boolean isShipping() {
        return this.value.equals(OrderStatus.SHIPPING.getValue());
    }

    public boolean isProcessing() {
        return this.value.equals(OrderStatus.PROCESSING.getValue());
    }


    public boolean isCancel() {
        return this.value.equals(OrderStatus.CANCEL.getValue());
    }

    public boolean isCompleted() {
        return this.value.equals(OrderStatus.COMPLETED.getValue());
    }




























//    Company
//
//            Order
//
//    Pending
//    Processing  ( event )
//    Shipped
//            Delivered
//    Completed
//     Canceled
//
//
// Shipping
   // pending
   // scheduled
   // picked
   // delivered
   // completed
    // cancelled


//    Shipping Transaction

    // unassigned
    // assigned
    // accepted
    // started
    // arrived
    // completed
    // taskDeleted
    // Declined
    // Cancelled


//
//    Agent
//
//            Order
//
//    Pending
//            Shipped
//    Delivered
//            Completed
//    Canceled
//
//            Shipping
//
//    Shipping Created
//    Pick Up
//    In Transit
//    Delivered
//            Cancel


}
