package org.iesbelen.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Pedido {

    private int id;
    private double total;
    private Date fecha;
    private int id_cliente;
    private int id_comercial;

    public Pedido() {}


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
}
