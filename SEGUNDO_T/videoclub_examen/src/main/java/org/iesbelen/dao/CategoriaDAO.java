package org.iesbelen.dao;

import org.iesbelen.domain.Categoria;
import org.iesbelen.domain.Pelicula;

import java.util.List;
import java.util.Optional;

public interface CategoriaDAO {

	public Optional<Categoria>  find(int id);
	public List<Categoria> getAll();

}
