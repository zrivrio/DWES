package org.iesbelen.videoclub.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesbelen.videoclub.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaCustomRepositoryJPQLimpl implements CategoriaCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Categoria> queryCustomCategoria(Optional<String> buscarOptional, Optional<String> ordenarOptional){
        StringBuilder querryBuilder = new StringBuilder("Select c from Categoria c ");
        if(buscarOptional.isPresent()){
            querryBuilder.append(" ").append("where c.nombre like :nombre ");
        }
        if(ordenarOptional.isPresent()){
            if( "asc".equalsIgnoreCase(ordenarOptional.get())){
                querryBuilder.append(" ").append("order by c.nombre asc ");
            } else if ( "desc".equalsIgnoreCase(ordenarOptional.get())){
                querryBuilder.append(" ").append("order by c.nombre desc ");
            }
        }
        Query query = em.createQuery(querryBuilder.toString());
        if(buscarOptional.isPresent()){
            query.setParameter("nombre", "%"+buscarOptional.get()+"%");
        }
        return query.getResultList();
    }
}
