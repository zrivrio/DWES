package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.exception.PeliculaNotFoundException;
import org.iesbelen.videoclub.repository.PeliculaRepository;
import org.iesbelen.videoclub.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    @Autowired
    private CategoriaService categoriaService;

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
//    public boolean addPeliculaCategoria(@PathVariable("id") Long id, @PathVariable("id_categoria") Long id_categoria) {
//        Pelicula pelicula = this.one(id);
//        Categoria categoriaAdd = this.categoriaService.one(id_categoria);
//        boolean res = false;
//          res = pelicula.getCategorias().add(categoriaAdd) &&
//        categoriaAdd.getPeliculas().add(pelicula);
//
//          if(res){
//          this.save(pelicula);
//          }
//          return res;
//    }

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
    public List<Pelicula> obtenerPeliculasConOrdenYPaginado(String[] orden, String[] paginado) {
        Sort sort = null;

        if (orden != null) {
            for (String criterio : orden) {
                String[] partes = criterio.split(",");
                if (partes.length == 2) {
                    String columna = partes[0];
                    String sentido = partes[1];
                    Sort.Order order = new Sort.Order("asc".equalsIgnoreCase(sentido) ? Sort.Direction.ASC : Sort.Direction.DESC, columna);
                    sort = (sort == null) ? Sort.by(order) : sort.and(Sort.by(order));
                }
            }
        }

        Pageable pageable = Pageable.unpaged();
        if (paginado != null && paginado.length == 2) {
            int pagina = Integer.parseInt(paginado[0]);
            int tamanio = Integer.parseInt(paginado[1]);
            pageable = PageRequest.of(pagina, tamanio, sort != null ? sort : Sort.unsorted());
        }

        Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);
        return peliculas.getContent();
    }


}
