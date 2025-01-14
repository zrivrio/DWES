package org.iesbelen.model;

public class Empleado {

    private int codigo;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int codigoDepartamento;

    public Empleado() {}

    public int getCodigo() {return codigo;}

    public String getNif() {return nif;}

    public String getNombre() {return nombre;}

    public String getApellido1() {return apellido1;}

    public String getApellido2() {return apellido2;}

    public int getCodigoDepartamento() {return codigoDepartamento;}

    public void setCodigo(int codigo) {this.codigo = codigo;}

    public void setNif(String nif) {this.nif = nif;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setApellido1(String apellido1) {this.apellido1 = apellido1;}

    public void setApellido2(String apellido2) {this.apellido2 = apellido2;}

    public void setCodigoDepartamento(int codigoDepartamento) {this.codigoDepartamento = codigoDepartamento;}

}
