package org.iesbelen.service;

import org.iesbelen.dao.ComercialDAO;
import org.iesbelen.dao.PedidoDAO;
import org.iesbelen.modelo.Comercial;
import org.iesbelen.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Pedido> listAll(){
        return pedidoDAO.getAll();
    }

    public List<Pedido> byIdComercial(Integer id) {
        return   pedidoDAO.getById_Comercial(id);

    }
}
