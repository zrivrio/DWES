package com.example.prueba.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.CodePointLength;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Perfil {

    @Id//Señalizar en la base de datos que esta es la primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")//Nombre que se le asigna en la base de datos
    private Long id;

    private String bio;

    @OneToOne //Especifica que un perfil esta asociado con un solo usuario
   @JoinColumn(name = "id_usuario", unique = true) //Define que la clave foranea en Perfil sera id_Usuario
    //Se asegura de que cada usuario tenga solo un perfil
    private Usuario usuario;//Un perdil pertenece a un solo usuario


}
