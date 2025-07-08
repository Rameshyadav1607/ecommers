package com.ecommers.resource;

import com.ecommers.domain.CustomerDomain;
import com.ecommers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String createAccount(@RequestBody CustomerDomain customerDomain){
        return  customerService.createAccount(customerDomain);
    }


}
