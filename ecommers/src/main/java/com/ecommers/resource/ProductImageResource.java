package com.ecommers.resource;

import com.ecommers.domain.ProductImageDomain;
import com.ecommers.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productimage")
public class ProductImageResource {
    @Autowired
    private ProductImageService productImageService;
    @PostMapping("/save")
    public String saveProductImage(@RequestBody ProductImageDomain productImageDomain){
        return productImageService.saveProductImage(productImageDomain);
    }
}
