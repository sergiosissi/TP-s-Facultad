package com.supermercado.producto;
import javax.persistence.*;

@Entity
public class ProductoSimple extends Producto {

    @Column(name = "precio")
    protected double precio;

    public ProductoSimple() {}

    public ProductoSimple(String nombre, double precio, int stock, String departamento) {
        super(nombre, stock, departamento);
        this.precio = precio;
    }

    // getters y setters
    public double getPrecio() {
        return precio;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
