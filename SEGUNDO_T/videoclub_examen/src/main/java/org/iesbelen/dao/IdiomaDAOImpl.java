package org.iesbelen.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.domain.Idioma;
import org.iesbelen.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring 
//que forma parte de la ‘capa de persistencia’.
@Repository
public class IdiomaDAOImpl implements IdiomaDAO {

	 //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	/**
	 * Devuelve lista con todos loa Clientes.
	 */
	@Override
	public List<Idioma> getAll() {
		
		List<Idioma> listaIdiomas = jdbcTemplate.query(
                "SELECT id_idioma, nombre FROM idioma",
                (rs, rowNum) -> new Idioma(rs.getInt("id_idioma"),
                						 	rs.getString("nombre")));
		
		log.info("Devueltos {} registros.", listaIdiomas.size());
		
        return listaIdiomas;
	}
}
