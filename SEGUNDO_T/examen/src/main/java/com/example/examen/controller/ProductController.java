package com.example.examen.controller;

import com.example.examen.domain.Product;
import com.example.examen.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!pagina", "!tamanio", "!campo", "!sentido"})
    public List<Product> all() {
        return this.productService.all();
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamanio", "!campo", "!sentido"})
    public List<Product> all(@RequestParam("buscar") Optional<String[]> buscar) {
       return  this.productService.findByColumnAndValue(buscar);
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!campo", "!sentido"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0")int pagina,
                                                    @RequestParam(value = "tamanio", defaultValue = "0") int tamanio) {
        Map<String, Object> response = this.productService.paginacion(pagina, tamanio);
        return ResponseEntity.ok(response) ;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!pagina", "!tamanio"})
    public List<Product> search(@RequestParam("campo") String campo,
                                @RequestParam("sentido") String sentido) {


        return this.productService.findByColumnAndSentido(campo, sentido);
    }


    @PostMapping({"","/"})
    public Product newProduct (@RequestBody Product product) {
        return this.productService.save(product);
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable("id") Long id) {
        return this.productService.one(id);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product ) {
        System.out.println(id);
        return this.productService.replace(id, product);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSocio(@PathVariable("id") Long id) {
        this.productService.delete(id);
    }


}
