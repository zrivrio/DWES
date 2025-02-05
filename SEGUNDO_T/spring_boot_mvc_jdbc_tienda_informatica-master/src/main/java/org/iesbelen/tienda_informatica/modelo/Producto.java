package org.iesbelen.tienda_informatica.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private int idFabricante;

    public Producto() {}
}
