package org.iesbelen.mapstruct;


import org.iesbelen.dto.ComercialDTO;
import org.iesbelen.modelo.Comercial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComercialMapper {

    @Mapping(target = "totalPedido", source = "totalPedido")
    @Mapping(target = "mediaPedido", source = "mediaPedido")
    public ComercialDTO comercialAComercialDTO(Comercial comercial, int totalPedido, double mediaPedido);




    public Comercial comercialDTOAcomercial(Comercial comercial);

}
