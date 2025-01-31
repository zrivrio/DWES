package org.iesbelen.modelo;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Comercial {

	private int id;
	@NotBlank(message = "{error.nombre}")
	@Size(max =30, message = "{error.nombre.size.max}")
	private String nombre;
	@NotBlank(message = "{error.apellido}")
	@Size(max =30, message = "{error.apellido.size.max}")
	private String apellido1;
	private String apellido2;
	@DecimalMax(value ="0.946")
	@DecimalMin(value ="0.276")
	private BigDecimal comision;

	public Comercial() {

	}
}
