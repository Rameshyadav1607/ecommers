package com.ecommers.domain;

import lombok.Data;

@Data
public class CustomerDomain {

    private String firstname;
    private String lastname;
    private UserDomain userdomain;
}
