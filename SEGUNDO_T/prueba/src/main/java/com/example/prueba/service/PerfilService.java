package com.example.prueba.service;

import com.example.prueba.domain.Orden;
import com.example.prueba.domain.Perfil;
import com.example.prueba.domain.Usuario;
import com.example.prueba.execption.OrdenNotFoundException;
import com.example.prueba.execption.PerfilNotFoundException;
import com.example.prueba.execption.ProductoNotFoundException;
import com.example.prueba.repository.OrdenRepository;
import com.example.prueba.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    public Perfil save(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public Perfil one(Long id) {
        return this.perfilRepository.findById(id)
                .orElseThrow(() -> new PerfilNotFoundException(id));
    }

    public void delete(Long id) {
        this.perfilRepository.findById(id).map(o -> {
            this.perfilRepository.delete(o);
            return o;
        }).orElseThrow(() -> new PerfilNotFoundException(id));
    }

    public Perfil replace(Long id, Perfil perfil) {
        return this.perfilRepository.findById(id).map( p -> (id.equals(perfil.getId())  ?
                        this.perfilRepository.save(perfil) : null))
                .orElseThrow(() -> new PerfilNotFoundException(id));
    }
}
