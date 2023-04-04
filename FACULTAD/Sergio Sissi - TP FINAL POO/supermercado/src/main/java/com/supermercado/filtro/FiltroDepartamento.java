package com.supermercado.filtro;

import com.supermercado.productos.Producto;

public class FiltroDepartamento implements Filtro {
    private String departamento;

    public FiltroDepartamento(String departamento) {
        this.departamento = departamento;
    }


    @Override
    public boolean cumple(Producto producto) {
        return (producto.getDepartamento().equals(this.departamento))?true:false;
    }


}

