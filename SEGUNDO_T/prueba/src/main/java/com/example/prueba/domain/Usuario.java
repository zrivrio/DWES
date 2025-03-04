package com.example.prueba.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Usuario {

    @Id //Señalizar en la base de datos que esta es la primary key
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria") //Nombre que se le asigna en la base de datos
    private Long id;

    private String nombre;

    @Embedded //Relacion embebida (los datos aparecen en la misma tabla)
    private Direccion direccion;

    //Indica que la relacion es gestionada por Perfil
    //Crea la relacion de uno a uno
    // Un usuario tiene un solo perfil
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Perfil perfil;

    //Crea la relacion de uno a muchos
    //Relacion de uno a muchos
    //Un usuario puede hacer muchas ordenes
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;
}
