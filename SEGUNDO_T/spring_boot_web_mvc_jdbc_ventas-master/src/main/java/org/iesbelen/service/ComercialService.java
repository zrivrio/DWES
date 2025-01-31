package org.iesbelen.service;

import org.iesbelen.dao.ComercialDAO;
import org.iesbelen.dao.PedidoDAO;
import org.iesbelen.dto.ComercialDTO;
import org.iesbelen.mapstruct.ComercialMapper;
import org.iesbelen.modelo.Comercial;
import org.iesbelen.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private ComercialMapper comercialMapper;

    public List<Comercial> listAll(){
        return comercialDAO.getAll();
    }

    public Comercial one(Integer id) {
        Optional<Comercial> optCo = comercialDAO.find(id);
        if (optCo.isPresent())
            return optCo.get();
        else
            return null;
    }

    public void newComercial(Comercial comercial) {

        comercialDAO.create(comercial);

    }

    public void replaceComercial(Comercial comercial) {

        comercialDAO.update(comercial);

    }

    public void deleteComercial(int id) {

        comercialDAO.delete(id);

    }

    public ComercialDTO comercialDTO(int id) {
        List<Pedido> listPedidoByComercia = pedidoDAO.getById_Comercial(id);
        Optional<Comercial> optCo = comercialDAO.find(id);

        Comercial comercial = optCo.get();



        int totalPedidos = pedidoDAO.getAll().size();

        System.out.println(totalPedidos);
        int totalPedidosByComercial = listPedidoByComercia.size();

        double mediaPedidos =  (double) totalPedidosByComercial / totalPedidos;
        mediaPedidos = mediaPedidos*100;

        return comercialMapper.comercialAComercialDTO(comercial, totalPedidosByComercial, mediaPedidos);
    }





}
