package com.ecommers.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ProductDomain {

    private int productId;
    private String categoryName;
    private String brandName;
    private String productName;
    private String description;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private List<Integer> colourIds;

}
