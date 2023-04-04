package com.supermercado.filtro;

import com.supermercado.productos.Producto;

public class FiltroPrecioMenor implements Filtro {
    private double precio;

    public FiltroPrecioMenor(double precio) {
        this.precio = precio;
    }


    @Override
    public boolean cumple(Producto producto) {
        return (producto.getPrecio() < this.precio)?true:false;
    }
}

