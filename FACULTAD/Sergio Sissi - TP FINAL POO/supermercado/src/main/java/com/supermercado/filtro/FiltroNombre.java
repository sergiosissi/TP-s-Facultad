package com.supermercado.filtro;

import com.supermercado.producto.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroNombre implements Filtro {
    private String nombre;

    public FiltroNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public boolean cumple(Producto producto) {
        return (producto.getDepartamento().equals(this.nombre))?true:false;
    }

}

