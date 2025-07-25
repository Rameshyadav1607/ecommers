package com.ecommers.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {CategoryValidator.class})
public @interface CategoryValid {

    String message() default "{com.sairam.ecommerce-microservices.validators." +
            "CategoryValid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
