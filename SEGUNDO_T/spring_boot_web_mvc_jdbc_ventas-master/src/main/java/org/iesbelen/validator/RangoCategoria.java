package org.iesbelen.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangoCategoriaValidator.class)
public @interface RangoCategoria {

String message() default "{rangoCategoria.mensaje}";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};

    int[] value() default {100, 200, 300, 400, 500, 600, 700, 800, 1000};
}
