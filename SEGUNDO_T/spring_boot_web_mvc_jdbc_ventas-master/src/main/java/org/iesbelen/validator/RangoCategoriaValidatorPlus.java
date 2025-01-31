package org.iesbelen.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RangoCategoriaValidatorPlus implements ConstraintValidator<RangoCategoria, Integer> {

    private static Set<Integer> categoriasValidas;

    @Override
    public void initialize(final RangoCategoria constraintAnnotation) {
        categoriasValidas = Arrays.stream(constraintAnnotation.value())
                .boxed()
                .collect(Collectors.toSet());
    }



    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
       return integer != null && categoriasValidas.contains(integer);
    }
}
