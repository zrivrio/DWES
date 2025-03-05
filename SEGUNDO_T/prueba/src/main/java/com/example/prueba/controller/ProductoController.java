package com.example.prueba.controller;

import com.example.prueba.domain.Producto;
import com.example.prueba.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping(value = {"", "/"}, params = {"!pagina", "!tamanio", "!orden"})
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping(value = {"", "/"}, params = {"!orden", "!paginado"})
    public ResponseEntity<Map<String, Object>> getAllProductosByPaginaAndTamanio(@RequestParam(value = "pagina", defaultValue = "1") int pagina, @RequestParam(value = "tamanio", defaultValue = "2") int tamanio) {
        Map<String, Object> response = productoService.getProductosPaginaTamanio(pagina, tamanio);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = {"", "/"}, params = {"!orden", "!pagina", "!tamanio"})
    public ResponseEntity<List<Producto>> getAllProductosByOrden(@RequestParam(value = "orden") String[] orden) {
        List<Producto> productos = productoService.getProductosOrden(orden);
        return ResponseEntity.ok(productos);
    }

    @GetMapping(value = {"", "/"}, params = {"!orden", "!pagina", "!tamanio"})
    public ResponseEntity<Map<String,Object>> getAllProductosByPaginacion(@RequestParam(value = "paginacion") String[] paginacion) {
        Map<String, Object> response = productoService.getProductosPaginacion(paginacion);
        return ResponseEntity.ok(response);
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
