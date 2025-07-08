package com.ecommers.service;


import com.ecommers.domain.ProductImageDomain;
import com.ecommers.model.Product;
import com.ecommers.model.ProductImage;
import com.ecommers.repository.ProductImageRepository;
import com.ecommers.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductRepository productRepository;

    public String saveProductImage(ProductImageDomain productImageDomain) {

        Product product = productRepository.findByProductId(productImageDomain.getProductId());
        if(product!= null){
            ProductImage productImage=new ProductImage();
            productImage.setProduct(product);
            productImage.setImageUrl(productImageDomain.getImageUrl());
            productImage.setIsMain(productImageDomain.getIsMain());
            productImage.setUploadedDate(LocalDate.now());
            productImageRepository.save(productImage);
            return "Product image saved/updated successfully";
         }
        else{
            return "product is not found with id"+ productImageDomain.getProductId();
        }


    }
}
