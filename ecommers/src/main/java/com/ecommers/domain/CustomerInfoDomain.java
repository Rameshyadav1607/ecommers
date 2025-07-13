package com.ecommers.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerInfoDomain {
    private Integer customerId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String mobileNumber;
    private Boolean active;
}
