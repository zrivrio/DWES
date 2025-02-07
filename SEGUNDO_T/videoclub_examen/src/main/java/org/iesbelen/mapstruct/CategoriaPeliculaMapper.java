package org.iesbelen.mapstruct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.iesbelen.domain.CategoriaPeliculas;
import org.iesbelen.domain.Pelicula;
import org.iesbelen.dto.CategoriaPeliculasDTO;
import org.iesbelen.dto.PeliculaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaPeliculaMapper {

    @Mapping(target = "categoria", source = "categoria")
    @Mapping(target = "nombre", source = "nombre")
    public CategoriaPeliculasDTO peliculacategoriaAPeliculacategoriaDTO(CategoriaPeliculas categoriaPeliculas, String categoria, String nombre);

}