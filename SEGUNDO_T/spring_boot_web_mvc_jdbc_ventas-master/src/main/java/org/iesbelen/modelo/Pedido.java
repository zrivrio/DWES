package org.iesbelen.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesbelen.validator.ValidarAno;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Pedido {

    private int id;
    private double total;
    //Para coger una fecha se coge la fecha de forma normal y se almacena en un localdate despues extraermo solo el año
    @ValidarAno
    private LocalDate fecha;
    private int id_cliente;
    private int id_comercial;

    public Pedido() {}

}
