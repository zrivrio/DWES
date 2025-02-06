package org.iesbelen.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesbelen.modelo.Cliente;
import org.iesbelen.modelo.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
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
	public synchronized void create(Pedido pedido) {

		//Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
		String sqlInsert = """
							INSERT INTO pedido (total, fecha, id_cliente, id_comercial) 
							VALUES  (   ?, ? , ? , ? )
						   """;

		KeyHolder keyHolder = new GeneratedKeyHolder();
		//Con recuperación de id generado
		int rows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
			int idx = 1;
			ps.setDouble(idx++, pedido.getTotal());
			ps.setDate(idx++, Date.valueOf(pedido.getFecha()));
			ps.setInt(idx++, pedido.getId_cliente());
			ps.setInt(idx++, pedido.getId_comercial());
			return ps;
		},keyHolder);

		pedido.setId(keyHolder.getKey().intValue());

//		Sin recuperación de id generado
//		int rows = jdbcTemplate.update(sqlInsert,
//							cliente.getNombre(),
//							cliente.getApellido1(),
//							cliente.getApellido2(),
//							cliente.getCiudad(),
//							cliente.getCategoria()
//					);

		log.info("Insertados {} registros.", rows);
	}


	@Override
	public List<Pedido> getAll() {
		
		List<Pedido> listPedido = jdbcTemplate.query(
                "SELECT * FROM pedido",
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                							  rs.getDouble("total"),
						                      rs.getDate("fecha").toLocalDate(),
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
                        rs.getDate("fecha").toLocalDate(),
						rs.getInt("id_cliente"),
						rs.getInt("id_comercial"))
				,id

		);

		log.info("Devueltos {} registros.", listPedidoById_Comercial.size());

		return listPedidoById_Comercial;
	}


}
