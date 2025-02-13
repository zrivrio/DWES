package org.iesbelen.videoclub.domain;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "socios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Socio{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    private String nombre;

    private String apellido;

    @OneToOne
    @JoinColumn( name = "tarjeta_id", referencedColumnName = "id")
    private Tarjeta tarjeta;

}
