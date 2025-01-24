package org.iesbelen.dao;

import org.iesbelen.modelo.Comercial;
import org.iesbelen.modelo.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {

	public List<Pedido> getAll();
	public List<Pedido> getById_Comercial(Integer id);


}
