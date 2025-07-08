package com.ecommers.resource;


import com.ecommers.domain.ProductDomain;
import com.ecommers.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductResource  {
    @Autowired
    private ProductService productService;
    @PostMapping("/save")
    public String saveProductOrUpdate(@RequestBody ProductDomain productDomain){
       return productService.saveProductOrUpdate(productDomain);
    }

    @GetMapping("/product/{productId}")
    public ProductDomain getProductByProductId(@PathVariable Integer productId){
       return productService.getProductByProductId(productId);
    }

    @GetMapping("/all/product")
    public List<ProductDomain> getAllProduct(){
        return  productService.getAllProduct();
    }
    @GetMapping("/{productName}")
    public List<ProductDomain> getProductByName(@PathVariable String productName){
        return  productService.getProductByName(productName);
    }
}
