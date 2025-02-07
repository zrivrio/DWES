package org.iesbelen.service;

import org.iesbelen.dao.CategoriaDAO;
import org.iesbelen.dao.CategoriaPeliculasDAO;
import org.iesbelen.dao.PeliculaDAO;
import org.iesbelen.domain.Categoria;
import org.iesbelen.domain.CategoriaPeliculas;
import org.iesbelen.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

	private CategoriaDAO categoriaDAO;

	@Autowired
	private CategoriaPeliculasDAO categoriaPeliculasDAO;

	@Autowired
	private PeliculaService peliculaService;

	public CategoriaService(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}
	public Categoria one(Integer id) {
		Optional<Categoria> optCat = categoriaDAO.find(id);
		if (optCat.isPresent())
			return optCat.get();
		else
			return null;
	}

	public List<Categoria> listAll() {

		return categoriaDAO.getAll();

	}

	public int conteoCateoria(int id) {
		List<CategoriaPeliculas> categorias = categoriaPeliculasDAO.getAll();

		int conteo = 0;
		for (CategoriaPeliculas cat : categorias) {
			if(cat.getId_categoria() == id)
				conteo++;
		}
		return conteo;
	}

	public double duracionMedia(int id) {
		List<CategoriaPeliculas> categorias = categoriaPeliculasDAO.getAll();
		List<Pelicula> peliculas = peliculaService.listAll();
		List<Integer> ids = new ArrayList<>();
		double duracion = 0;

		int conteo = 0;
		for (CategoriaPeliculas cat : categorias) {
			if(cat.getId_categoria() == id)
				ids.add(cat.getId_pelicula());
			conteo++;
		}
		for( int idPelicula : ids){
			for(Pelicula p : peliculas){
				if (p.getId_pelicula() == idPelicula){
					duracion += p.getDuracion();
				}
			}
		}
		duracion = duracion/conteo;



		return duracion;
	}

}
