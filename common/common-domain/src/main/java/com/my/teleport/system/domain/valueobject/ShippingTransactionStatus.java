package com.my.teleport.system.domain.valueobject;

public enum ShippingTransactionStatus {


    UNASSIGNED(100, "shippingTransactionStatus.unassigned"),
    ASSIGNED(200, "shippingTransactionStatus.assigned"),
    ACCEPTED(300, "shippingTransactionStatus.accepted"),
    STARTED(400, "shippingTransactionStatus.started"),
    ARRIVED(500, "shippingTransactionStatus.arrived"),
    COMPLETED(600, "shippingTransactionStatus.completed"),
    TASKDELETED(700, "shippingTransactionStatus.taskdeleted"),
    DECLINED(800, "shippingTransactionStatus.declined"),
    CANCELLED(900, "shippingTransactionStatus.cancelled");


    private final Integer value;
    private final String code;

    ShippingTransactionStatus(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public static ShippingTransactionStatus fromInt(final Integer statusValue) {

        ShippingTransactionStatus enumeration = ShippingTransactionStatus.UNASSIGNED;
        switch (statusValue) {
            case 100:
                enumeration = ShippingTransactionStatus.UNASSIGNED;
                break;
            case 200:
                enumeration = ShippingTransactionStatus.ASSIGNED;
                break;
            case 300:
                enumeration = ShippingTransactionStatus.ACCEPTED;
                break;
            case 400:
                enumeration = ShippingTransactionStatus.STARTED;
                break;
            case 500:
                enumeration = ShippingTransactionStatus.ARRIVED;
                break;
            case 600:
                enumeration = ShippingTransactionStatus.COMPLETED;
                break;
            case 700:
                enumeration = ShippingTransactionStatus.TASKDELETED;
                break;
            case 800:
                enumeration = ShippingTransactionStatus.DECLINED;
                break;
            case 900:
                enumeration = ShippingTransactionStatus.CANCELLED;
                break;
        }
        return enumeration;
    }

    public boolean hasStateOf(final ShippingTransactionStatus state) {
        return this.value.equals(state.getValue());
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isUnassigned() {
        return this.value.equals(ShippingTransactionStatus.UNASSIGNED.getValue());
    }

    public boolean isAssigned() {
        return this.value.equals(ShippingTransactionStatus.ASSIGNED.getValue());
    }

    public boolean isAccepted() {
        return this.value.equals(ShippingTransactionStatus.ACCEPTED.getValue());
    }

    public boolean isCompleted() {
        return this.value.equals(ShippingTransactionStatus.COMPLETED.getValue());
    }

    public boolean isTaskDeleted() {
        return this.value.equals(ShippingTransactionStatus.TASKDELETED.getValue());
    }

    public boolean isDeclined() {
        return this.value.equals(ShippingTransactionStatus.DECLINED.getValue());
    }

    public boolean isCancelled() {
        return this.value.equals(ShippingTransactionStatus.CANCELLED.getValue());
    }



}
