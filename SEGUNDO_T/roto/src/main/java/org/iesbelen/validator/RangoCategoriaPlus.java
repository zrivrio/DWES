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
//Si queremos que se repiata la misma ananotacion en el mismo atributo
//@Repeatable(org.iesbelen.validator.ChequearNombre.List.class)
public @interface RangoCategoriaPlus {

    String message() default "{RangoCategoria.mensaje}";

    //Con esto se recibe el array con los valores inicializados
    int[] values();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    //Implementar el array que recoge la posible repetición de la anotación
  //  @Target(ElementType.FIELD)
  //  @Retention(RetentionPolicy.RUNTIME)
 //   @Documented
 //   @interface List {
  //      ChequearNombre[] value();
   // }

}