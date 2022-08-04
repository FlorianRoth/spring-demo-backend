package com.example.demo.api.validation;

import com.example.demo.api.dto.HasId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdNotNullValidator implements ConstraintValidator<IdNotNullConstraint, HasId> {
    @Override
    public void initialize(IdNotNullConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(HasId value, ConstraintValidatorContext context) {
        return value != null && value.getId() != null;
    }
}
