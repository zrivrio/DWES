package org.iesbelen.dao;

import org.iesbelen.model.Artista;
import org.iesbelen.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface ArtistaDAO {
    public void create(Artista artista);
    public List<Artista> getAll();
    public Optional<Artista> find(int id);
    public void update(Artista artista);
    public void delete(int id);
}
