package com.example.prueba.controller;

import com.example.prueba.domain.Usuario;
import com.example.prueba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(params = {" ", "/"})
    public List<Usuario> getUsuarios() {
        return usuarioService.findAll();
    }


}
