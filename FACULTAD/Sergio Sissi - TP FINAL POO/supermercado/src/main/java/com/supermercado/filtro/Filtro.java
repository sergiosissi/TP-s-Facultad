package com.supermercado.filtro;

import com.supermercado.producto.Producto;

import java.util.List;

public interface Filtro {

    public abstract boolean cumple(Producto producto);

}

