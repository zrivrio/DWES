package org.iesbelen.tienda_informatica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoDTO {

    private int idProducto;
    private String nombre;
    private double precio;
    private int idFabricante;
    private String NombreFabricante;

    public ProductoDTO() {}
}
