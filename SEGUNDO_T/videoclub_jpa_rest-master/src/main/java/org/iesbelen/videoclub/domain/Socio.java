package org.iesbelen.videoclub.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;


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

    @ElementCollection //Crea una tabla relacionada entre la tabla de address y socio
    @CollectionTable(name = "socio_addresses",joinColumns = @JoinColumn(name = "socio_id"))
    @AttributeOverrides({
            @AttributeOverride( name = "houseNumber", column = @Column(name = "house_number")),
            @AttributeOverride( name = "street", column = @Column(name = "street")),
            @AttributeOverride( name = "city", column = @Column(name = "city")),
            @AttributeOverride( name = "zipCode", column = @Column(name = "zipCode"))
    })
    private Set<Address> addresses = new HashSet<>();

    @Embedded //Dntro de la tabla socio crea unos atributos de address
    //Solo tiene una direccion
    private Address mainAddress;

    @ElementCollection
    @CollectionTable(name = "persone_phone_numbers", joinColumns = @JoinColumn(name = "socio_id"))
    @Column(name = "phone_number")
    private Set<String> phoneNumbers = new HashSet<>();

    @OneToOne
    @JoinColumn( name = "tarjeta_id", referencedColumnName = "id")
    private Tarjeta tarjeta;

}
