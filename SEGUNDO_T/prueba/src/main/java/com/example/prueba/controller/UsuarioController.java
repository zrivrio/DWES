package com.example.prueba.controller;

import com.example.prueba.domain.Usuario;
import com.example.prueba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({"", "/"})
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @PostMapping({"", "/"})
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable long id) {
        return usuarioService.one(id);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        return usuarioService.replace(id, usuario);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable long id) {
        this.usuarioService.delete(id);
    }
    

}
