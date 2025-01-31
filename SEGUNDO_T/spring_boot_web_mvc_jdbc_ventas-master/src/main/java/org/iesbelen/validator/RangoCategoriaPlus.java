package org.iesbelen.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangoCategoriaValidatorPlus.class)
public @interface RangoCategoriaPlus {

    String message() default "{RangoCategoria.mensaje}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    //Con esto se recibe el array con los valores inicializados
    int[] values();

}