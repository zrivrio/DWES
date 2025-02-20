package org.iesbelen.videoclub.repository;

import org.iesbelen.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaCustomRepository {

    List<Categoria> queryCustomCategoria(Optional<String> buscarOptional, Optional<String> ordenarOptional);
}
