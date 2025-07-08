package com.ecommers.repository;

import com.ecommers.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    Brand findByBrandName(String brandName);
    @Query("select b.brandName from Brand b")
    List<String> findAllBrandNames();
}
