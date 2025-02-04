package org.iesbelen.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesbelen.modelo.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
@Repository
//Utilizo lombok para generar el constructor
@AllArgsConstructor
public class PedidoDAOImpl implements PedidoDAO {

	//JdbcTemplate se inyecta por el constructor de la clase automáticamente
	//
	private JdbcTemplate jdbcTemplate;


	@Override
	public List<Pedido> getAll() {
		
		List<Pedido> listPedido = jdbcTemplate.query(
                "SELECT * FROM pedido",
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                							  rs.getDouble("total"),
                							  rs.getDate("fecha"),
                							  rs.getInt("id_cliente"),
                							  rs.getInt("id_comercial"))
                						 	
        );
		
		log.info("Devueltos {} registros.", listPedido.size());
		
        return listPedido;
	}

	@Override
	public List<Pedido> getById_Comercial(Integer id) {
		List<Pedido> listPedidoById_Comercial = jdbcTemplate.query(
				"SELECT * FROM pedido where id_comercial = ?",
				(rs, rowNum) -> new Pedido(rs.getInt("id"),
						rs.getDouble("total"),
						rs.getDate("fecha"),
						rs.getInt("id_cliente"),
						rs.getInt("id_comercial"))
				,id

		);

		log.info("Devueltos {} registros.", listPedidoById_Comercial.size());

		return listPedidoById_Comercial;
	}


}
