package org.iesbelen.mapstruct;

import org.iesbelen.domain.Pelicula;
import org.iesbelen.dto.PeliculaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeliculaMapper {

    @Mapping(target = "idioma", source = "idioma")
    public PeliculaDTO peliculaAPeliculaDTO(Pelicula pelicula, String idioma);


}