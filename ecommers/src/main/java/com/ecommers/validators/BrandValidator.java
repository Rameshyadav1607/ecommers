package com.ecommers.validators;


import com.ecommers.domain.BrandDomain;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BrandValidator implements ConstraintValidator<BrandValid, BrandDomain> {
    @Override
    public void initialize(BrandValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BrandDomain brandDomain, ConstraintValidatorContext context) {

        boolean isBrandValid=true;

        if(brandDomain.getBrandName()==null || brandDomain.getBrandName().isEmpty() ){
          context.buildConstraintViolationWithTemplate("error.brand.brandname.required")
                  .addPropertyNode("brandName")
                  .addConstraintViolation();

          isBrandValid=false;

        }

        if(brandDomain.getDescription()==null || brandDomain.getBrandName().isEmpty()){
            context.buildConstraintViolationWithTemplate("error.brand.brandDecription.required")
                    .addPropertyNode("discription")
                    .addConstraintViolation();
            isBrandValid=false;
        }

        return isBrandValid;
    }
}
