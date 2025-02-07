package org.iesbelen.service;

import org.iesbelen.dao.PeliculaDAO;
import org.iesbelen.domain.Categoria;
import org.iesbelen.domain.Idioma;
import org.iesbelen.domain.Pelicula;
import org.iesbelen.dto.PeliculaDTO;
import org.iesbelen.mapstruct.PeliculaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeliculaService {

	@Autowired
	private PeliculaDAO peliculaDAO;

	@Autowired
	private PeliculaMapper peliculaMapper;

	@Autowired
	private IdiomaService idomaService;


	
	public List<Pelicula> listAll() {
		
		return peliculaDAO.getAll();
		
	}


	public void newPelicula(Pelicula pelicula) {

		peliculaDAO.create(pelicula);

	}

	public List<PeliculaDTO> listDTO() {

		List<Idioma> listaIdiomas = idomaService.listAll();
		List<Pelicula> listaPeliculas = peliculaDAO.getAll();


		return listaPeliculas.stream()
				.map(pelicula -> {
					Idioma idioma = listaIdiomas.stream()
							.filter(i -> i.getId_idioma() == pelicula.getId_idioma())
							.findFirst()
							.orElse(null);

					return (idioma != null )
							? peliculaMapper.peliculaAPeliculaDTO(pelicula, idioma.getNombre())
							: null;
				})
				.filter(dto -> dto != null)
				.collect(Collectors.toList());
	}

}
