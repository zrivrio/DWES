package org.iesbelen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PedidoDTO {

    private int id;
    private double total;
    private Date fecha;
    private int id_cliente;
    private int id_comercial;
    private String nombre_cliente;


    public PedidoDTO() {}

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public int getId_comercial() {
        return id_comercial;
    }

    public String getNombre_cliente() { return nombre_cliente;}
}
