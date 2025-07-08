package com.ecommers.domain;

import lombok.Data;

@Data
public class DeliveryAddressDomain {

    private Integer deliveryAddressId;
    private Integer customerId;
    private String addressLine;
    private String city;
    private String state;
    private int pincode;
    private String country;
    private String type;
}
