package org.iesbelen.service;

import org.iesbelen.dao.CategoriaPeliculasDAO;
import org.iesbelen.domain.Categoria;
import org.iesbelen.domain.CategoriaPeliculas;
import org.iesbelen.domain.Pelicula;
import org.iesbelen.dto.CategoriaPeliculasDTO;
import org.iesbelen.mapstruct.CategoriaPeliculaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaPeliculasService {
 	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaPeliculasDAO categoriaPeliculasDAO;

	@Autowired
	private CategoriaPeliculaMapper categoriaPeliculaMapper;

	@Autowired
	private PeliculaService peliculaService;


	/*public List<CategoriaPeliculasDTO> listDTO() {
		List<CategoriaPeliculas> listaCategoriaPeliculas = categoriaPeliculasDAO.getAll();
		List<Categoria> listaCategoria = categoriaService.listAll();
		List<Pelicula> listaPeliculas = peliculaService.listAll();


		return listaCategoriaPeliculas.stream()
				.map(categoriaPeliculas -> {
					Pelicula pelicula = listaPeliculas.stream()
							.filter(p -> p.getId_pelicula() == categoriaPeliculas.getId_pelicula())
							.findFirst()
							.orElse(null);
					Categoria categoria = listaCategoria.stream()
							.filter(p -> p.getId_categoria() == categoriaPeliculas.getId_categoria())
							.findFirst()
							.orElse(null);
					CategoriaPeliculas categoriaPeliculasDTO = new CategoriaPeliculas();
					categoriaPeliculasDTO.setId_pelicula(pelicula.getId_pelicula());
					categoriaPeliculasDTO.setId_categoria(categoria.getId_categoria());

					return (pelicula != null && categoria != null )
							? categoriaPeliculaMapper.peliculacategoriaAPeliculacategoriaDTO(categoriaPeliculasDTO, categoria.getNombre(), pelicula.getTitulo())
							: null;
				})
				.filter(dto -> dto != null)
				.collect(Collectors.toList());
	}*/

}
