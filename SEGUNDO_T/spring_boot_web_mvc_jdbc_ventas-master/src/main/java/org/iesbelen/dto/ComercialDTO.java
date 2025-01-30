package org.iesbelen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ComercialDTO {

    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float comision;


    private int totalPedido;
    private double mediaPedido;

    public ComercialDTO() {}

}
