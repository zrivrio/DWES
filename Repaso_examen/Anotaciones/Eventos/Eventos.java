import java.lang.annotation.*;
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Eventos {
    Evento[] value();
}
