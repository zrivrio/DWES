package org.iesbelen.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.domain.Categoria;
import org.iesbelen.domain.CategoriaPeliculas;
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
public class CategoriaPeliculasDAOImpl implements CategoriaPeliculasDAO {

	 //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
	 @Autowired
	 private JdbcTemplate jdbcTemplate;

	@Override
	public List<CategoriaPeliculas> getAll() {

		List<CategoriaPeliculas> listaCategoria = jdbcTemplate.query(
				"SELECT id_categoria, id_pelicula FROM pelicula_categoria",
				(rs, rowNum) -> new CategoriaPeliculas(rs.getInt("id_categoria"),
						rs.getInt("id_pelicula")));

		log.info("Devueltos {} registros.", listaCategoria.size());

		return listaCategoria;
	}
}
