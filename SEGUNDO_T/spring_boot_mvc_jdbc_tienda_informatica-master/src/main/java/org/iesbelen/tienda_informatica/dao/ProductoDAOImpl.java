package org.iesbelen.tienda_informatica.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.tienda_informatica.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ProductoDAOImpl implements ProductoDAO {

	 @Autowired
	 private JdbcTemplate jdbcTemplate;

	@Override	
	public synchronized void create(Producto producto) {

		String sqlInsert = """
							INSERT INTO productos (nombre, precio, idFabricante) 
							VALUES  (     ?,         ?)
						   """;

		KeyHolder keyHolder = new GeneratedKeyHolder();
		//Con recuperación de id generado
		int rows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
			int idx = 1;
			ps.setString(idx++, producto.getNombre());
			ps.setDouble(idx++, producto.getPrecio());
			ps.setInt(idx, producto.getIdFabricante());
			return ps;
		},keyHolder);

		producto.setIdProducto(keyHolder.getKey().intValue());
		
	}

	@Override
	public List<Producto> getAll() {
		
		List<Producto> listProd = jdbcTemplate.query(
                "SELECT * FROM productos",
                (rs, rowNum) -> new Producto(rs.getInt("idProducto"),rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("idFabricante"))
        );
			
        return listProd;
        
	}

	@Override
	public Optional<Producto> find(int id) {
		
		Producto pro =  jdbcTemplate
				.queryForObject("SELECT * FROM productos WHERE idProducto = ?"
								, (rs, rowNum) -> new Producto(rs.getInt("idProducto"),
								rs.getString("nombre"),
								rs.getDouble("precio"),
								rs.getInt("idFabricante"))
								, id
								);
		
		if (pro != null) return Optional.of(pro);
		else return Optional.empty();
        
	}
	/**
	 * Actualiza productos con campos del bean productos según ID del mismo.
	 */
	@Override
	public void update(Producto producto) {
		
		int rows = jdbcTemplate.update("UPDATE productos SET nombre = ?, precio = ?, idFabricante = ?  WHERE idProducto = ?", producto.getNombre(), producto.getPrecio(), producto.getIdFabricante(), producto.getIdProducto());
		if (rows == 0) System.out.println("Update de productos con 0 registros actualizados.");
    
	}

	/**
	 * Borra productos con ID proporcionado.
	 */
	@Override
	public void delete(int id) {
		
		int rows = jdbcTemplate.update("DELETE FROM productos WHERE idProducto = ?", id);
		
		if (rows == 0) System.out.println("Update de productos con 0 registros actualizados.");
		
	}

}
