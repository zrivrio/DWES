package org.iesbelen.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pelicula {
    private int id_pelicula;
    @NotBlank(message = "{error.nombre}")
    @Size(min = 2, message = "{error.nombre.size.min}" )
    private String titulo;
    @Size(max = 300, message = "{error.descripcion.size.max}")
    private String descripcion;
    @Min(value = 1895, message = "{error.anyo}")
    private int anyo_lanzamiento;
    private int id_idioma;
    @Min(value = 1, message = "{error.duracion}")
    private int duracion;

    public Pelicula() {}
}
