package org.iesbelen.service;

import org.iesbelen.dao.ClienteDAO;
import org.iesbelen.dao.PedidoDAO;
import org.iesbelen.dto.PedidoDTO;
import org.iesbelen.mapstruct.PedidoMapper;
import org.iesbelen.modelo.Cliente;
import org.iesbelen.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;
    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private PedidoMapper pedidoMapper;

    public List<Pedido> listAll(){
        return pedidoDAO.getAll();
    }

    public List<Pedido> listPedidos(int idComercial) {

        List<Pedido> pedidos = pedidoDAO.getById_Comercial(idComercial);

        return pedidos;
    }

    public List<PedidoDTO> listPedidosDTO(int idComercial) {

        List<Cliente> clientes = clienteDAO.getAll();
        List<Pedido> pedidos = pedidoDAO.getById_Comercial(idComercial);


        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (Pedido p : pedidos) {
            int idC = p.getId_cliente();
            for (Cliente c : clientes) {
                if (c.getId() == idC) {
                    pedidosDTO.add(pedidoMapper.pedidoApedidoDTO(p, c.getNombre() + " " + c.getApellido1()
                            + " " + (c.getApellido2() != null ? c.getApellido2() : "")));
                }
            }
        }
        return pedidosDTO;
    }

    public void newPedido(Pedido pedido) {

        pedidoDAO.create(pedido);

    }

  /*

  Un dto en el que se añade el nombre de cliente y el nombre de comercial al pedido
  public List<PedidoDTO> listPedidosDTO() {

        List<Cliente> listaCliente = clienteDAO.getAll();
        List<Comercial> listaComercial = comercialDAO.getAll();
        List<Pedido> listPedidos = pedidoDAO.getAll();


        return listPedidos.stream()
                .map(pedido -> {
                    Cliente cliente = listaCliente.stream()
                            .filter(c -> c.getId() == pedido.getId_cliente())
                            .findFirst()
                            .orElse(null);

                    Comercial comercial = listaComercial.stream()
                            .filter(c -> c.getId() == pedido.getId_comercial())
                            .findFirst()
                            .orElse(null);

                    return (cliente != null && comercial != null)
                            ? pedidoMapper.pedidoAPedidosDTO(pedido, cliente.getNombre(), comercial.getNombre())
                            : null;
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }*/
}
