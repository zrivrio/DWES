package org.iesbelen.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidarAnoValidator.class)
public @interface ValidarAno {

        String message() default "El año no es válido";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
