package org.iesbelen.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")

//Para que funcione la colecion Set<Pelicula> en Categoria
@EqualsAndHashCode(of = "nombre")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private long id;

    @NaturalId
    private String nombre;

    @ManyToMany(
            mappedBy = "categorias")
    Set<Pelicula> peliculas = new HashSet<>();


}