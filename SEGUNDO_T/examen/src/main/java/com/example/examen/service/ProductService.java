package com.example.examen.service;

import com.example.examen.domain.Product;
import com.example.examen.exception.ProductNotFoundException;
import com.example.examen.repository.ProductCustomRepository;
import com.example.examen.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCustomRepository productCustomRepository;


    public List<Product> all() {
        return this.productRepository.findAll();
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public Product one(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product replace(Long id, Product product) {
        System.out.println(id);

        return this.productRepository.findById(id).map( p -> (id.equals(product.getId_product())  ?
                                                            this.productRepository.save(product) : null))
                .orElseThrow();

    }

    public void delete(Long id) {
        this.productRepository.findById(id).map(p -> {this.productRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> findByColumnAndValue(Optional<String[]> buscar) {
        return this.productCustomRepository.findByColumnAndValue(buscar);
    }

    public Map<String, Object> paginacion(int pagina, int tamanio){
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Product> products = this.productRepository.findAll(pageable);
        Map<String, Object> resultado = new HashMap<>();

        resultado.put("total", products.getTotalElements());
        resultado.put("totalPages", products.getTotalPages());
        resultado.put("currentPage", products.getNumber());
        resultado.put("items", products.getContent());
        return resultado ;
    }

    public List<Product> findByColumnAndSentido(String campo, String sentido) {

        return this.productCustomRepository.findByColumnAndSentido(campo, sentido);

    }

}
