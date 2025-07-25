package com.ecommers.model;
// Generated 31-May-2025, 5:05:44 pm by Hibernate Tools 4.0.1.Final


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Brand generated by hbm2java
 */
@Entity
@Table(name="brand"
    ,schema="public"
)
public class Brand implements java.io.Serializable {


     private int brandId;
     private String brandName;
     private String description;
     private LocalDateTime createdDate;
     private LocalDateTime updatedDate;
     private Set<Product> products = new HashSet<Product>(0);

    public Brand() {
    }

	
    public Brand(int brandId, String brandName, LocalDateTime createdDate) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.createdDate = createdDate;
    }
    public Brand(int brandId, String brandName, String description, LocalDateTime createdDate, LocalDateTime updatedDate, Set<Product> products) {
       this.brandId = brandId;
       this.brandName = brandName;
       this.description = description;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.products = products;
    }
   
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="brand_id", unique=true, nullable=false)
    public int getBrandId() {
        return this.brandId;
    }
    
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    
    @Column(name="brand_name", nullable=false, length=100)
    public String getBrandName() {
        return this.brandName;
    }
    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", nullable=false, length=29)
    public LocalDateTime getcreatedDate() {
        return this.createdDate;
    }
    
    public void setcreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date", length=29)
    public LocalDateTime getupdatedDate() {
        return this.updatedDate;
    }
    
    public void setupdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="brand")
    public Set<Product> getProducts() {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }




}


