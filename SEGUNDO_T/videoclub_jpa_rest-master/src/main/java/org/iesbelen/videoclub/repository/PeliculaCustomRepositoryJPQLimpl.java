package org.iesbelen.videoclub.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesbelen.videoclub.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaCustomRepositoryJPQLimpl implements PeliculaCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Pelicula> queryCustomPeliculas(Optional<String []> ordenarOptional) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Pelicula p");

       if (ordenarOptional.isPresent()) {
           String[] ordenar = ordenarOptional.get();
           if (ordenar.length == 2) {
               queryBuilder.append(" ORDER BY ");
               String columna = ordenar[0];
               String dirreccion = ordenar[1];
               queryBuilder.append("p."+columna+" ")
                       .append(dirreccion.equalsIgnoreCase("asc")? "ASC" : "DESC");
           }
       }
       return  em.createQuery(queryBuilder.toString(), Pelicula.class).getResultList();
    }
}
