package org.iesbelen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PeliculaDTO {
    private int id_pelicula;
    private String titulo;
    private String descripcion;
    private int anyo_lanzamiento;
    private int id_idioma;
    private String idioma;
    private int duracion;

    public PeliculaDTO() {}
}
