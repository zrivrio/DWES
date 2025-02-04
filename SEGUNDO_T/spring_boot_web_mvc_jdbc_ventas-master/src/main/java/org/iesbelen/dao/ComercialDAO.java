package org.iesbelen.dao;

import org.iesbelen.modelo.Comercial;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {
	
	public void create(Comercial cliente);
	
	public List<Comercial> getAll();
	public Optional<Comercial>  find(int id);
	
	public void update(Comercial cliente);
	
	public void delete(long id);

}
