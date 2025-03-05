package com.example.prueba.repository;

import com.example.prueba.domain.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoCustomRepository {

    List<Producto> queryCustomProductos(Optional<String[] > ordenarOptional);

}
