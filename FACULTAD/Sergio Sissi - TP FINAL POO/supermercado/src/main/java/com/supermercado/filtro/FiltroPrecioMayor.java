package com.supermercado.filtro;

import com.supermercado.producto.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPrecioMayor implements Filtro {
    private double precio;

    public FiltroPrecioMayor(double precio) {
        this.precio = precio;
    }


    @Override
    public boolean cumple(Producto producto) {
        return (producto.getPrecio() > this.precio)?true:false;
    }

}

