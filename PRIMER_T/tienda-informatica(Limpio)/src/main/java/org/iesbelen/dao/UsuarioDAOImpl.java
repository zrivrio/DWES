package org.iesbelen.dao;

import org.iesbelen.model.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl extends AbstractDAOImpl implements UsuarioDAO {


    @Override
    public void create(Usuario usuario) {

    }

    @Override
    public List<Usuario> getAll() {
        return List.of();
    }

    @Override
    public Optional<Usuario> find(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Usuario usuario) {

    }
}
