package com.ecommers.resource;

import com.ecommers.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountResource {
    @Autowired
    private DiscountService discountService;
}
