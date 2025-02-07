package org.iesbelen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaPeliculasDTO {
    int id_categoria;
    int id_pelicula;
    String categoria;
    String nombre;

}
