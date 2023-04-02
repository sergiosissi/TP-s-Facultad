package com.supermercado.persona;

import com.supermercado.producto.Producto;
import com.supermercado.producto.ProductoSimple;

import javax.persistence.*;

@Entity
public class Empleado extends Persona {

    @Column(name = "legajo")
    private String legajo;

    public Empleado() {}

    public Empleado(String nombre, String apellido, String dni, String telefono, String legajo) {
        super(nombre, apellido, dni, telefono);
        this.legajo = legajo;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public void agregarStock(Producto producto, int cantidad) {
        producto.aumentarStock(cantidad);
    }

    public void modificarPrecio(ProductoSimple producto, double nuevoPrecio) {
        producto.setPrecio(nuevoPrecio);
    }
}

