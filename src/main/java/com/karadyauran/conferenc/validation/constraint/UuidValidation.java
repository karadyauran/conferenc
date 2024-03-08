package com.karadyauran.conferenc.validation.constraint;

import com.karadyauran.conferenc.validation.interf.Uuid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;
import java.util.UUID;

public class UuidValidation implements ConstraintValidator<Uuid, UUID>
{

    private static final String UUID_PATTERN
            = "^[\\da-fA-F]{8}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{12}$";

    @Override
    public void initialize(Uuid constraintAnnotation)
    {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context)
    {
        return Optional.ofNullable(value.toString())
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(UUID_PATTERN))
                .orElse(false);
    }
}
