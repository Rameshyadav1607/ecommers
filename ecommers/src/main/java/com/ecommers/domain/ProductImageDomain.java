package com.ecommers.domain;


import com.ecommers.model.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImageDomain {

    private Integer productId;
    private String imageUrl;
    private Boolean isMain;
}
