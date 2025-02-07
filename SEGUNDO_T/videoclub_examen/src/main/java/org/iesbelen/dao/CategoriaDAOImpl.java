package org.iesbelen.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.domain.Categoria;
import org.iesbelen.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring 
//que forma parte de la ‘capa de persistencia’.
@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

	 //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
	 @Autowired
	 private JdbcTemplate jdbcTemplate;

	/**
	 * Devuelve Optional de Cliente con el ID dado.
	 */
	@Override
	public Optional<Categoria> find(int id) {
		
		Categoria cat =  jdbcTemplate.queryForObject("SELECT id_categoria, nombre FROM categoria WHERE id_categoria = ?"
								, (rs, rowNum) -> new Categoria(rs.getInt("id_categoria"),
            						 						rs.getString("nombre")), id);
		
		if (cat != null) {
			return Optional.of(cat);}
		else { 
			log.info("Cliente no encontrado.");
			return Optional.empty(); }
        
	}

	@Override
	public List<Categoria> getAll() {

		List<Categoria> listaCategoria = jdbcTemplate.query(
				"SELECT id_categoria, nombre FROM categoria",
				(rs, rowNum) -> new Categoria(rs.getInt("id_pelicula"),
						rs.getString("titulo")));

		log.info("Devueltos {} registros.", listaCategoria.size());

		return listaCategoria;
	}
}
