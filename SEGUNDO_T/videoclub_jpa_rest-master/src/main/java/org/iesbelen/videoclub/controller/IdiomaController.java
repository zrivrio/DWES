package org.iesbelen.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.videoclub.domain.Categoria;
import org.iesbelen.videoclub.domain.Idioma;
import org.iesbelen.videoclub.service.CategoriaService;
import org.iesbelen.videoclub.service.IdiomaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/idioma")
public class IdiomaController {
    private final IdiomaService idiomaService;

    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    @GetMapping({"","/"})
    public List<Idioma> all() {
        log.info("Accediendo a todas las idioma");
        return this.idiomaService.all();
    }

    @PostMapping({"","/"})
    public Idioma newIdioma(@RequestBody Idioma idioma) {
        return this.idiomaService.save(idioma);
    }

    @GetMapping("/{id}")
    public Idioma one(@PathVariable("id") Long id) {
        return this.idiomaService.one(id);
    }

    @PutMapping("/{id}")
    public Idioma replaceIdioma(@PathVariable("id") Long id, @RequestBody Idioma idioma ) {
        return this.idiomaService.replace(id, idioma);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteIdioma(@PathVariable("id") Long id) {
        this.idiomaService.delete(id);
    }


}
