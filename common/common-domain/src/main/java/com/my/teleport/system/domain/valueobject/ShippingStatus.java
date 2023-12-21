package com.my.teleport.system.domain.valueobject;

public enum ShippingStatus {

    PENDING(100, "shippingStatus.pending"),
    SCHEDULED(200, "shippingStatus.scheduled"),
    INITIATED( 300, "shippingStatus.inititated"),
    PICKEDUP(400, "shippingStatus.picked"),
    DELIVERED(500, "shippingStatus.delivered"),
    COMPLETED(600, "shippingStatus.completed"),
    CANCELLED(700, "shippingStatus.cancelled");


    private final Integer value;
    private final String code;

    ShippingStatus(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public static ShippingStatus fromInt(final Integer statusValue) {

        ShippingStatus enumeration = ShippingStatus.PENDING;
        switch (statusValue) {
            case 100:
                enumeration = ShippingStatus.PENDING;
                break;
            case 200:
                enumeration = ShippingStatus.SCHEDULED;
                break;
            case 300:
                enumeration = ShippingStatus.INITIATED;
                break;
            case 400:
                enumeration = ShippingStatus.PICKEDUP;
                break;
            case 500:
                enumeration = ShippingStatus.DELIVERED;
                break;
            case 600:
                enumeration = ShippingStatus.COMPLETED;
                break;
            case 700:
                enumeration = ShippingStatus.CANCELLED;
                break;
        }
        return enumeration;
    }

    public boolean hasStateOf(final ShippingStatus state) {
        return this.value.equals(state.getValue());
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isPending() {
        return this.value.equals(ShippingStatus.PENDING.getValue());
    }

    public boolean isScheduled() {
        return this.value.equals(ShippingStatus.SCHEDULED.getValue());
    }

    public boolean isInitiated() {
        return this.value.equals(ShippingStatus.INITIATED.getValue());
    }

    public boolean isPickedUp() {
        return this.value.equals(ShippingStatus.PICKEDUP.getValue());
    }

    public boolean isDelivered() {
        return this.value.equals(ShippingStatus.DELIVERED.getValue());
    }

    public boolean isCompleted() {
        return this.value.equals(ShippingStatus.COMPLETED.getValue());
    }


    public boolean isCancelled() {
        return this.value.equals(ShippingStatus.CANCELLED.getValue());
    }



}
