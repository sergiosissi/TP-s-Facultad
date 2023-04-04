package com.supermercado.filtro;

import com.supermercado.productos.Producto;

public class FiltroStockMenor implements Filtro {
    private int stock;

    public FiltroStockMenor(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean cumple(Producto producto) {
        return (producto.getStock() < this.stock)?true:false;
    }
}

