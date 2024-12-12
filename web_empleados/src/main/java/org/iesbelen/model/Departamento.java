package org.iesbelen.model;

public class Departamento {
    private int codigo;
    private String nombre;
    private double presupuestos;
    private double gastos;

    public Departamento() {}

    public int getCodigo() {return codigo;}

    public String getNombre() {return nombre;}

    public double getGastos() {return gastos;}

    public double getPresupuestos() {return presupuestos;}

    public void setCodigo(int codigo) {this.codigo = codigo;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setPresupuestos(double presupuestos) {this.presupuestos = presupuestos;}

    public void setGastos(double gastos) {this.gastos = gastos;}
}
