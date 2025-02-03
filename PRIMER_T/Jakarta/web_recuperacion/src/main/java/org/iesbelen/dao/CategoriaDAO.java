package org.iesbelen.dao;

import org.iesbelen.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaDAO {
		
	//public void create(Categoria categoria);
	public List<Categoria> getAll();
	public Optional<Categoria> find(int id);
	public void update(Categoria categoria);
	//public void delete(int id);

}
