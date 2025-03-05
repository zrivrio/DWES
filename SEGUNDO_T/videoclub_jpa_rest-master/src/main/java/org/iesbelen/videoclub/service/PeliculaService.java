package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.exception.PeliculaNotFoundException;
import org.iesbelen.videoclub.repository.PeliculaCustomRepository;
import org.iesbelen.videoclub.repository.PeliculaRepository;
import org.iesbelen.videoclub.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private PeliculaCustomRepository peliculaCustomRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {

        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }

    public Pelicula save(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getIdPelicula())  ?
                                                            this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {this.peliculaRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    private Pelicula peliculasDuracionMenor(int cantidad){
        return this.peliculaRepository.findByDuracionLessThan(cantidad);
    }

    public Map<String, Object> all(int pagina, int tamanio){
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("idPelicula").ascending());
        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginado);
        Map<String, Object> resultado = new HashMap<>();


        resultado.put("total", pageAll.getTotalElements());
        resultado.put("totalPages", pageAll.getTotalPages());
        resultado.put("peliculas", pageAll.getContent());
        resultado.put("currentPage", pageAll.getNumber());

        return resultado;
    }

    public List<Pelicula> ordenarPeliculasCuston(String[] orden) {
       return peliculaCustomRepository.queryCustomPeliculas(Optional.of(orden));
       //return this.peliculaRepository.findAll(Sort.by(orden[0]))
    }

    public List<Pelicula> ordenarPeliculas(String[] orden) {
        Sort sort = null;
        if (orden != null && orden.length == 2) {
            String column = orden[0];
            String sentido = orden[1];
            if ("asc".equalsIgnoreCase(sentido)) {
                sort = Sort.by(column).ascending();
            }else if ("desc".equalsIgnoreCase(sentido)) {
                sort = Sort.by(column).descending();
            }
        }
        return peliculaRepository.findAll(sort);
    }

    public Map<String, Object> allPaginado(String[] paginacion) {
     return this.all(Integer.parseInt(paginacion[0]), Integer.parseInt(paginacion[1]));
    }




}
