package com.my.teleport.system.domain.valueobject;

import java.util.Objects;

public class Address {

    private final String id;

    private final String street;

    private final String houseNo;

    private final String suburb;

    private final String floor;

    private final String deliveryInstructions;
    private final String postalCode;

    private final Integer region;

    private final Integer township;

    private final String labelCode;

    public Address(String id, String street, String houseNo, String suburb, String floor, String deliveryInstructions, String postalCode, Integer region, Integer township, String labelCode) {
        this.id = id;
        this.street = street;
        this.houseNo = houseNo;
        this.suburb = suburb;
        this.floor = floor;
        this.deliveryInstructions = deliveryInstructions;
        this.postalCode = postalCode;
        this.region = region;
        this.township = township;
        this.labelCode = labelCode;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public String getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Integer getRegion() {
        return region;
    }

    public Integer getTownship() {
        return township;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getFloor() {
        return floor;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }



}
