package com.example.prueba.controller;

import com.example.prueba.domain.Producto;
import com.example.prueba.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping({"", "/"})
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @PostMapping({"", "/"})
    public Producto addProducto(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id) {
        return productoService.one(id);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.replace(id, producto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.delete(id);
    }



}
