package com.ecommers.service;


import com.ecommers.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService  {
    @Autowired
    private DiscountRepository discountRepository;
}
