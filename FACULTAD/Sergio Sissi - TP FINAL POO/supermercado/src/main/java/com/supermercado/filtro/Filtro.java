package com.supermercado.filtro;

import com.supermercado.productos.Producto;

public interface Filtro {

    public abstract boolean cumple(Producto producto);

}

