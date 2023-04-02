package com.supermercado.filtro;

import com.supermercado.producto.Producto;

import java.util.List;
import java.util.stream.Collectors;

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

