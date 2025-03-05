package com.example.prueba.service;

import com.example.prueba.domain.Perfil;
import com.example.prueba.domain.Producto;
import com.example.prueba.domain.Usuario;
import com.example.prueba.execption.PerfilNotFoundException;
import com.example.prueba.execption.ProductoNotFoundException;
import com.example.prueba.repository.PerfilRepository;
import com.example.prueba.repository.ProductoCustomRepository;
import com.example.prueba.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoCustomRepository productoCustomRepository;

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

    public Map<String, Object> getProductosPaginaTamanio(int pagina, int tamanio){
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());
        Page<Producto> page = productoRepository.findAll(paginado);
        Map<String, Object> resultado = new HashMap<>();

        resultado.put("total", page.getTotalElements());
        resultado.put("totalPages", page.getTotalPages());
        resultado.put("items", page.getContent());
        resultado.put("pagina", page.getNumber());
        return resultado;

    }

    public List<Producto> getProductosOrden(String[] orden) {
        return productoCustomRepository.queryCustomProductos(Optional.of(orden));
    }

    public Map<String, Object> getProductosPaginacion(String[] paginacion){
       return this.getProductosPaginaTamanio(Integer.parseInt(paginacion[0]), Integer.parseInt(paginacion[1]));
    }


}
