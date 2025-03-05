package com.example.prueba.repository;

import com.example.prueba.domain.Producto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductosCustomRepositoryJPQLimpl implements  ProductoCustomRepository{

    @Autowired
    private EntityManager em;

    @Override
    public List<Producto> queryCustomProductos(Optional<String[]> ordenarOptional) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Producto p");

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
        return  em.createQuery(queryBuilder.toString(), Producto.class).getResultList();
    }
}
