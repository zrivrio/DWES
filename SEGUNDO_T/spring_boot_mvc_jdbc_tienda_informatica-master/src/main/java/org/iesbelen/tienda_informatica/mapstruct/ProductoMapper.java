package org.iesbelen.tienda_informatica.mapstruct;

import org.iesbelen.tienda_informatica.dto.ProductoDTO;
import org.iesbelen.tienda_informatica.modelo.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "nombreFabricante", source = "nombreFabricante")
    public ProductoDTO toDto(Producto producto, String nombreFabricante);

    public Producto toNormal(Producto producto);
}
