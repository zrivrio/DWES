package com.example.prueba.controller;

import com.example.prueba.domain.Perfil;
import com.example.prueba.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping({"", "/"})
    public List<Perfil> getPerfil() {
        return perfilService.findAll();
    }

    @PostMapping({"", "/"})
    public Perfil addPerfil(@RequestBody Perfil perfil) {
        return perfilService.save(perfil);
    }
}
