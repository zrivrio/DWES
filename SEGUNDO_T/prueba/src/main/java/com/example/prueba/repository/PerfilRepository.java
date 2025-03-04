package com.example.prueba.repository;

import com.example.prueba.domain.Orden;
import com.example.prueba.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
