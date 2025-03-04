package com.example.prueba.service;

import com.example.prueba.domain.Orden;
import com.example.prueba.domain.Usuario;
import com.example.prueba.execption.OrdenNotFoundException;
import com.example.prueba.execption.ProductoNotFoundException;
import com.example.prueba.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    public Orden one(Long id) {
        return this.ordenRepository.findById(id)
                .orElseThrow(() -> new OrdenNotFoundException(id));
    }

    public void delete(Long id) {
        this.ordenRepository.findById(id).map(o -> {
            this.ordenRepository.delete(o);
            return o;
        }).orElseThrow(() -> new OrdenNotFoundException(id));
    }

    public Orden replace(Long id, Orden orden) {
        return this.ordenRepository.findById(id).map( p -> (id.equals(orden.getId())  ?
                        this.ordenRepository.save(orden) : null))
                .orElseThrow(() -> new OrdenNotFoundException(id));
    }
}
