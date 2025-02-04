package org.iesbelen.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.*;
import java.util.stream.Collectors;

public class RangoCategoriaValidatorPlus implements ConstraintValidator<RangoCategoriaPlus, Integer> {

    private List<Integer> categoriasValidas;

    @Override
    public void initialize( RangoCategoriaPlus valoresAlmacemados) {
        categoriasValidas = new ArrayList<>();
         for(int valor : valoresAlmacemados.values()){
             categoriasValidas.add(valor);
         }

    }



    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer != null && categoriasValidas.contains(integer);
    }
}