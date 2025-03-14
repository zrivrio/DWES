package org.iesbelen.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.videoclub.domain.Categoria;
import org.iesbelen.videoclub.domain.Pelicula;
import org.iesbelen.videoclub.repository.PeliculaCustomRepository;
import org.iesbelen.videoclub.repository.PeliculaRepository;
import org.iesbelen.videoclub.service.CategoriaService;
import org.iesbelen.videoclub.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pelicula")
public class PeliculaController {
    private final PeliculaService peliculaService;

    @Autowired
    private CategoriaService categoriaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamanio", "!orden" , "!paginado"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }

    @GetMapping(value = {"","/"}, params = {"!orden", "!paginado"})
    public ResponseEntity<Map<String,Object>> all(@RequestParam(value = "pagina" , defaultValue = "0") int pagina,
                                                  @RequestParam(value = "tamanio" , defaultValue = "0") int tamanio) {
        Map<String,Object> response = this.peliculaService.all(pagina,tamanio);
        return  ResponseEntity.ok(response);

    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamanio", "!paginado"})
    public ResponseEntity<List<Pelicula>> getPeliculasOrden(@RequestParam(name = "orden", required = false) String[] orden) {
       List<Pelicula> peliculas = this.peliculaService.ordenarPeliculasCuston(orden);
       return ResponseEntity.ok(peliculas);
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamanio", "!orden"})
    public ResponseEntity<Map<String, Object>> getPeliculasPaginado(@RequestParam(name = "paginado", required = false) String[] paginado) {

        Map<String, Object> response = this.peliculaService.allPaginado(paginado);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        return this.peliculaService.replace(id, pelicula);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }

    @PostMapping("/{id}/add/{id_categoria}")
    public void addPeliculaCategoria(@PathVariable("id") Long id, @PathVariable("id_categoria") Long id_categoria) {
        Pelicula pelicula = this.peliculaService.one(id);
        Categoria categoriaAdd = this.categoriaService.one(id_categoria);

        pelicula.getCategorias().add(categoriaAdd);
        categoriaAdd.getPeliculas().add(pelicula);
        this.categoriaService.replace(id_categoria, categoriaAdd);
        this.peliculaService.replace(id, pelicula);
    }



}
