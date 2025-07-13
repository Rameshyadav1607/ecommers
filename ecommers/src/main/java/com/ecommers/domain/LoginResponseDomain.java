package com.ecommers.domain;

import lombok.Data;

@Data
public class LoginResponseDomain {

    private String token;
    private String username;
    private String email;
    private String mobileNumber;
    private String userRole;
}
