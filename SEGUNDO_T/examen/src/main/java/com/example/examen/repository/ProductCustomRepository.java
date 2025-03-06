package com.example.examen.repository;

import com.example.examen.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCustomRepository {
    List<Product> findByColumnAndValue(Optional<String[]> buscar);

    List<Product> findByColumnAndSentido(String column, String sentido);


}
