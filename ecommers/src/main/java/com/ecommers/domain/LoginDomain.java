package com.ecommers.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDomain {

    private  String emailIdOrPhoneNumner;
    private String  password;
}
