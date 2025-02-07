package org.iesbelen.controller;


import jakarta.validation.Valid;
import org.iesbelen.domain.CategoriaPeliculas;
import org.iesbelen.domain.Idioma;
import org.iesbelen.domain.Pelicula;
import org.iesbelen.dto.CategoriaPeliculasDTO;
import org.iesbelen.dto.PeliculaDTO;
import org.iesbelen.service.CategoriaPeliculasService;
import org.iesbelen.service.CategoriaService;
import org.iesbelen.service.IdiomaService;
import org.iesbelen.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private IdiomaService idiomaService;

    @Autowired
    private CategoriaPeliculasService categoriaPeliculasService;


    @GetMapping("")
    public String listar(Model model) {
        List<PeliculaDTO> listaPeliculaDTO = peliculaService.listDTO();
       // List<CategoriaPeliculasDTO> categoriaPeliculasDTOS = categoriaPeliculasService.listDTO();
       // model.addAttribute("categoriaPeliculasDTOS", categoriaPeliculasDTOS);
        model.addAttribute("listaPeliculaDTO", listaPeliculaDTO);
        return "peliculas/peliculas";
    }


    @GetMapping("/crear")
    public String crear(Model model) {
      //  List<CategoriaPeliculasDTO> categoriaPeliculasDTOS = categoriaPeliculasService.listDTO();
        List<Idioma> listaIdioma = idiomaService.listAll();
        Pelicula pelicula = new Pelicula();

      //  model.addAttribute("categoriaPeliculasDTOS", categoriaPeliculasDTOS);
        model.addAttribute("listaIdioma", listaIdioma);
        model.addAttribute("pelicula", pelicula);

        return "peliculas/crear-pelicula";

    }

    @PostMapping("/crear")
    public String submitCrear(@Valid @ModelAttribute("pelicula") Pelicula pelicula, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pelicula", pelicula);
            return "peliculas/crear-pelicula";
        }
        peliculaService.newPelicula(pelicula);

        return "redirect:/peliculas" ;

    }

}
