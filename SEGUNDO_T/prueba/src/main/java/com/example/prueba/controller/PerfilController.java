package com.example.prueba.controller;

import com.example.prueba.domain.Perfil;
import com.example.prueba.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping({"", "/"})
    public List<Perfil> getAllPerfiles() {
        return perfilService.findAll();
    }

    @PostMapping({"", "/"})
    public Perfil addPerfil(@RequestBody Perfil perfil) {
        return perfilService.save(perfil);
    }

    @GetMapping("/{id}")
    public Perfil getPerfil(@PathVariable Long id) {
        return perfilService.one(id);
    }

    @PutMapping("/{id}")
    public Perfil updatePerfil(@PathVariable Long id, @RequestBody Perfil perfil) {
        return perfilService.replace(id, perfil);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePerfil(@PathVariable Long id) {
        perfilService.delete(id);
    }

}
