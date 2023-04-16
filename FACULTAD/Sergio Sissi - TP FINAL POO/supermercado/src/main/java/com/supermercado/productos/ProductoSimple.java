package com.supermercado.productos;
import javax.persistence.*;

@Entity
@Table(name ="producto_simple")
@PrimaryKeyJoinColumn(name = "producto_id", referencedColumnName = "id")
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

    @Override
    public String toString() {
        // Devuelve una cadena formateada con la información deseada
        return "Nombre: " + getNombre() + ", Stock: " + getStock() + ", Departamento: " + getDepartamento();
    }

}
