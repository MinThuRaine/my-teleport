package com.my.teleport.system.domain.valueobject;

public enum ShippingType {

    PICKUP_AND_DELIVERY(1, "shippingType.pickupanddelivery"),
    PICKUP(2, "shippingType.pickup"),
    DELIVERY(3, "shippingType.delivery");



    private final Integer value;
    private final String code;

    ShippingType(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public static ShippingType fromInt(final Integer statusValue) {

        ShippingType enumeration = ShippingType.PICKUP_AND_DELIVERY;

        switch (statusValue) {
            case 1:
                enumeration = ShippingType.PICKUP_AND_DELIVERY;
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

    public boolean isPickupAndDelivery() {
        return this.value.equals(ShippingType.PICKUP_AND_DELIVERY.getValue());
    }

}
