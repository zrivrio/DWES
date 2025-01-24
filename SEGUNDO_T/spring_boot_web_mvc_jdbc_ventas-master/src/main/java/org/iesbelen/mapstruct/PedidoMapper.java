package org.iesbelen.mapstruct;

import org.iesbelen.dto.PedidoDTO;
import org.iesbelen.modelo.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "nombreCliente", source = "nombreCliente")
    public PedidoDTO pedidoApedidoDTO(Pedido pedido, String nombreCliente);


    public Pedido pedidoDTOApedido(Pedido pedido);

}
