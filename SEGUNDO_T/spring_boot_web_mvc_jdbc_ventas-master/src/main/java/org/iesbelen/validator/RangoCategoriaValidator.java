package org.iesbelen.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class RangoCategoriaValidator implements ConstraintValidator<RangoCategoria, Integer> {

    private static final Set<Integer> categoriasValidas = new HashSet<>();

    static {
        categoriasValidas.add(100);
        categoriasValidas.add(200);
        categoriasValidas.add(300);
        categoriasValidas.add(400);
        categoriasValidas.add(500);
        categoriasValidas.add(600);
        categoriasValidas.add(700);
        categoriasValidas.add(800);
        categoriasValidas.add(1000);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer != null && categoriasValidas.contains(integer);
    }
}