package com.supermercado.filtro;

import com.supermercado.productos.Producto;

public class FiltroNombre implements Filtro {
    private String nombre;

    public FiltroNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public boolean cumple(Producto producto) {
        return (producto.getNombre().equals(this.nombre))?true:false;
    }

}

