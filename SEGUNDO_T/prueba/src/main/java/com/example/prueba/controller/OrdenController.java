package com.example.prueba.controller;

import com.example.prueba.domain.Orden;
import com.example.prueba.domain.Perfil;
import com.example.prueba.service.OrdenService;
import com.example.prueba.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;

    @GetMapping({"", "/"})
    public List<Orden> getAllOrdenes() {
        return ordenService.findAll();
    }

    @PostMapping({"", "/"})
    public Orden addOrden(@RequestBody Orden orden) {
        return ordenService.save(orden);
    }

    @GetMapping("/{id}")
    public Orden getOrden(@PathVariable Long id) {
        return ordenService.one(id);
    }

    @PutMapping("/{id}")
    public Orden updateOrden(@PathVariable Long id, @RequestBody Orden orden) {
        return ordenService.replace(id, orden);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteOrden(@PathVariable Long id) {
        ordenService.delete(id);
    }

}
