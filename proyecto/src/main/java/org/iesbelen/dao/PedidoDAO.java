package org.iesbelen.dao;

import org.iesbelen.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {
    public void create(Pedido pedido);
    public List<Pedido> getAll();
    public Optional<Pedido> find(int id);
    public void update(Pedido pedido);
    public void delete(int id);
}
