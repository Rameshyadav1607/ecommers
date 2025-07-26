package com.ecommers.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerInfoDomain {
    private Integer customerId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String mobileNumber;
    private Boolean active;
}
