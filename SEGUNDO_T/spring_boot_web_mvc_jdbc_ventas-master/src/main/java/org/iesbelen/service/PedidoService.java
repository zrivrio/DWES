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
}
