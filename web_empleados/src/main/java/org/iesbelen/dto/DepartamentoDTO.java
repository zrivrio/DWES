package org.iesbelen.dto;

import org.iesbelen.model.Departamento;

public class DepartamentoDTO extends Departamento {

    private int nEmpleados;

    public DepartamentoDTO(Departamento departamento, int nEmpleados) {
        this.setCodigo(departamento.getCodigo());
        this.setNombre(departamento.getNombre());
        this.setPresupuestos(departamento.getPresupuestos());
        this.setGastos(departamento.getGastos());
        this.setnEmpleados(nEmpleados);
    }

    public int getnEmpleados() {return nEmpleados;}
    public void setnEmpleados(int nEmpleados) {this.nEmpleados = nEmpleados;}
}
