package org.iesbelen.dao;

import org.iesbelen.model.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoDAO {
    public void create(DetallePedido detallePedido);
    public List<DetallePedido> getAll();
    public Optional<DetallePedido> find(int id);
    public void delete(int id);
}
