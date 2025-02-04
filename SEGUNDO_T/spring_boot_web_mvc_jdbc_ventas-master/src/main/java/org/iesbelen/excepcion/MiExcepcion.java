package org.iesbelen.excepcion;

public class MiExcepcion extends Exception{
    private static final long serialVersionUID = 1L;
    public MiExcepcion(String s){
        super("{error.mi.error}");
    }
}
