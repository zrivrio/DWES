package com.example.prueba.service;

import com.example.prueba.domain.Perfil;
import com.example.prueba.domain.Producto;
import com.example.prueba.domain.Usuario;
import com.example.prueba.execption.PerfilNotFoundException;
import com.example.prueba.execption.ProductoNotFoundException;
import com.example.prueba.repository.PerfilRepository;
import com.example.prueba.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto one(Long id) {
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public void delete(Long id) {
        this.productoRepository.findById(id).map(o -> {
            this.productoRepository.delete(o);
            return o;
        }).orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public Producto replace(Long id, Producto producto) {
        return this.productoRepository.findById(id).map( p -> (id.equals(producto.getId())  ?
                        this.productoRepository.save(producto) : null))
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }
}
