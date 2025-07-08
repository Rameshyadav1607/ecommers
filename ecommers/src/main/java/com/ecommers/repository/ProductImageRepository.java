package com.ecommers.repository;

import com.ecommers.model.Product;
import com.ecommers.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {

    ProductImage findByProductProductName(Product product);
}
