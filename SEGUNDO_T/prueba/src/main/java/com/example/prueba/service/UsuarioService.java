package com.example.prueba.service;

import com.example.prueba.domain.Perfil;
import com.example.prueba.domain.Producto;
import com.example.prueba.domain.Usuario;
import com.example.prueba.execption.PerfilNotFoundException;
import com.example.prueba.execption.ProductoNotFoundException;
import com.example.prueba.execption.UsuarioNotFoundException;
import com.example.prueba.repository.PerfilRepository;
import com.example.prueba.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario one(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public void delete(Long id) {
        this.usuarioRepository.findById(id).map(o -> {
            this.usuarioRepository.delete(o);
            return o;
        }).orElseThrow(() -> new UsuarioNotFoundException(id));
    }
    public Usuario replace(Long id, Usuario usuario) {
        return this.usuarioRepository.findById(id).map( p -> (id.equals(usuario.getId())  ?
                        this.usuarioRepository.save(usuario) : null))
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }
}
