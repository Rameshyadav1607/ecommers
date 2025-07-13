package com.ecommers.domain;

import lombok.Data;

@Data
public class UserDomain {

    private String username;
    private String email;
    private String mobileNumber;
    private String password;
    private String conformPassword;

}
