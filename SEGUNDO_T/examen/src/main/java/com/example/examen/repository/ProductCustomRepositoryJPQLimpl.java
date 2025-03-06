package com.example.examen.repository;

import com.example.examen.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductCustomRepositoryJPQLimpl  implements ProductCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Product> findByColumnAndValue(Optional<String[]> buscar) {
        StringBuilder queryBuilder = new StringBuilder("Select p from Product p ");
        if( buscar.isPresent()){
            if (buscar.get()[0] == null) {
                queryBuilder.append(" WHERE p.name = :name ");
            } else {
                queryBuilder.append(" WHERE p." + buscar.get()[0]);
            }
            queryBuilder.append(" like: valor");
        }
        Query query = em.createQuery(queryBuilder.toString());
        if (buscar.isPresent()) {
            query.setParameter("valor", "%"+buscar.get()[1]+"%");
        }
        return query.getResultList();
    }

    @Override
    public List<Product> findByColumnAndSentido(String campo, String sentido) {
        StringBuilder queryBuilder = new StringBuilder("Select p from Product p ");
        if ("name".equals(campo) || "price".equals(campo)) {
            if("name".equals(campo)) {
                queryBuilder.append(" WHERE p.name = :name ");
            } if("price".equals(campo)) {
                queryBuilder.append(" WHERE p.price = :price");
            }
        }else {
            queryBuilder.append(" WHERE p.name ");
        }
        queryBuilder.append("ORDER BY p." + campo + " ").append(sentido.equalsIgnoreCase("asc") ? "ASC" : "DESC");


        return em.createQuery(queryBuilder.toString(), Product.class).getResultList();
    }


}
