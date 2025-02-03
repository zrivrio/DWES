package org.iesbelen.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RangoCategoriaValidatorPlus implements ConstraintValidator<RangoCategoriaPlus, Integer> {

    int[] categoriasValidas;

    @Override
    public void initialize( RangoCategoriaPlus valoresAlmacemados) {
        categoriasValidas = valoresAlmacemados.values();

    }



    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer != null && categoriasValidas.contains(integer);
    }
}