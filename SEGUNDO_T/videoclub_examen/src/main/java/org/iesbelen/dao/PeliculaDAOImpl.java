package org.iesbelen.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring 
//que forma parte de la ‘capa de persistencia’.
@Repository
public class PeliculaDAOImpl implements PeliculaDAO {

	 //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
	/**
	 * Inserta en base de datos el nuevo Cliente, actualizando el id en el bean Cliente.
	 */
	@Override	
	public synchronized void create(Pelicula pelicula) {
		
							//Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
		String sqlInsert = """
							INSERT INTO pelicula (titulo, descripcion, anyo_lanzamiento, id_idioma, duracion) 
							VALUES  (     ?,         ?,         ?,       ?,         ?)
						   """;
		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int rows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
			int idx = 1;
			ps.setString(idx++, pelicula.getTitulo());
			ps.setString(idx++, pelicula.getDescripcion());
			ps.setInt(idx++, pelicula.getAnyo_lanzamiento());
			ps.setInt(idx++, pelicula.getId_idioma());
			ps.setInt(idx, pelicula.getDuracion());
			return ps;
		},keyHolder);
		
		pelicula.setId_pelicula(keyHolder.getKey().intValue());

		log.info("Insertados {} registros.", rows);
	}

	/**
	 * Devuelve lista con todos loa Clientes.
	 */
	@Override
	public List<Pelicula> getAll() {
		
		List<Pelicula> listaPeliculas = jdbcTemplate.query(
                "SELECT id_pelicula, titulo, descripcion, anyo_lanzamiento, id_idioma , duracion FROM pelicula",
                (rs, rowNum) -> new Pelicula(rs.getInt("id_pelicula"),
                						 	rs.getString("titulo"),
                						 	rs.getString("descripcion"),
                						 	rs.getInt("anyo_lanzamiento"),
                						 	rs.getInt("id_idioma"),
                						 	rs.getInt("duracion")
                						 	)
        );
		
		log.info("Devueltos {} registros.", listaPeliculas.size());
		
        return listaPeliculas;
	}
}
