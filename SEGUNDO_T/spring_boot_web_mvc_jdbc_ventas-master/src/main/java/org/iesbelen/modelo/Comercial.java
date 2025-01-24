package org.iesbelen.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercial {

	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private float comision;

	public Comercial() {}


	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public float getComision() {
		return comision;
	}

	public String getApellido2() {
		return apellido2;
	}
}
