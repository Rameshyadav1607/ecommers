package com.ecommers.repository;


import com.ecommers.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.productName = ?1")
    Product findByProductName(String categoryName);

    @Query("select p from Product p where p.productName = ?1")
    List<Product> findAllByProductName(String productName);

    Product findByProductId(Integer productId);
}
