package com.ecommers.resource;

import com.ecommers.domain.LoginDomain;
import com.ecommers.domain.LoginResponseDomain;
import com.ecommers.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginResource {
    @Autowired
    private LoginService loginService;

    @PostMapping()
public LoginResponseDomain createAuthentication(@RequestBody LoginDomain loginDomain){

    return  loginService.createAuthentication(loginDomain);
}

}

