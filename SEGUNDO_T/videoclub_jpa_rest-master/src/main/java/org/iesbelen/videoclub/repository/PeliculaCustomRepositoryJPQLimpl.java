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
    public List<Pelicula> queryCustomPeliculas(Optional<List<String>> ordenarOptional) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Pelicula p");

        if (ordenarOptional.isPresent() && !ordenarOptional.get().isEmpty()) {
            queryBuilder.append(" ORDER BY ");
            List<String> ordenes = ordenarOptional.get();

            List<String> criteriosOrden = new ArrayList<>();
            for (String orden : ordenes) {
                String[] partes = orden.split(",");
                if (partes.length == 2) {
                    String columna = partes[0];
                    String sentido = partes[1].equalsIgnoreCase("desc") ? "DESC" : "ASC";
                    criteriosOrden.add("p." + columna + " " + sentido);
                }
            }
            queryBuilder.append(String.join(", ", criteriosOrden));
        }

        Query query = em.createQuery(queryBuilder.toString(), Pelicula.class);
        return query.getResultList();
    }


}
