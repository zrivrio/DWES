package org.iesbelen.videoclub.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;


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

    @NaturalId
    private String dni;

    private String nombre;

    private String apellido;

    @OneToOne
    @JoinColumn( name = "tarjeta_id", referencedColumnName = "id")
    private Tarjeta tarjeta;

}
