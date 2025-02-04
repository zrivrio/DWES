package org.iesbelen.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class RangoCategoriaValidator implements ConstraintValidator<RangoCategoria, Integer> {

    private static  Set<Integer> categoriasValidas = new HashSet<>();

    @Override
    public void initialize(RangoCategoria constraintAnnotation) {
          categoriasValidas = Set.of(100,200,300,400,500,600,700,800,900);
    }


    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer != null && categoriasValidas.contains(integer);
    }
}