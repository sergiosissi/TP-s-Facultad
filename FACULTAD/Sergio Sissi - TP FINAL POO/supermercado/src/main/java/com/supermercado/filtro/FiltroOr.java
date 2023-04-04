package com.supermercado.filtro;

import com.supermercado.productos.Producto;

public class FiltroOr implements Filtro {
    private Filtro filtro1;
    private Filtro filtro2;

    public FiltroOr(Filtro filtro1, Filtro filtro2) {
        this.filtro1 = filtro1;
        this.filtro2 = filtro2;
    }

    @Override
    public boolean cumple(Producto producto){
        return (this.filtro1.cumple(producto) && this.filtro2.cumple(producto));
    }

}
