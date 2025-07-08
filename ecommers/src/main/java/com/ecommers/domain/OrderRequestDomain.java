package com.ecommers.domain;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDomain {
    private Integer customerId;
    private Integer deliveryAddressId;
    private List<RequestItemsDomain> requestItemsDomainList;

}
