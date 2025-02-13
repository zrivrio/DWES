package org.iesbelen.videoclub.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table( name = "tarjetas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarjeta {

    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    private Date caducidad;

    @OneToOne(mappedBy = "tarjeta")
    private Socio socio;
}
