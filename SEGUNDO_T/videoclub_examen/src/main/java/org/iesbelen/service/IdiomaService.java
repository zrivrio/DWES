package org.iesbelen.service;

import org.iesbelen.dao.CategoriaDAO;
import org.iesbelen.dao.IdiomaDAO;
import org.iesbelen.domain.Categoria;
import org.iesbelen.domain.Idioma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdiomaService {

	@Autowired
	private IdiomaDAO idiomaDAO;


	public List<Idioma> listAll() {

		return idiomaDAO.getAll();

	}

}
