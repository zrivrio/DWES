package org.iesbelen.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidarAñoValidator implements ConstraintValidator<ValidarAño, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) return false; // O puedes permitir null
        int year = value.getYear();
        int currentYear = LocalDate.now().getYear();
        return year >= 1900 && year <= currentYear;
    }
}
