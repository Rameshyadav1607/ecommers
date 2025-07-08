package com.ecommers.domain;


import com.ecommers.validators.BrandValid;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@BrandValid
public class BrandDomain {

    private int brandId;
    private String brandName;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
