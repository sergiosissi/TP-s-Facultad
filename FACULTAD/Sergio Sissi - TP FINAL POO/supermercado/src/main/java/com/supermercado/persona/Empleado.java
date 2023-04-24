package com.supermercado.persona;

import com.supermercado.filtro.Filtro;
import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoSimple;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(name = "id_persona", referencedColumnName = "id")
public class Empleado extends Persona {

    @Column(name = "legajo")
    private String legajo;

    public Empleado() {}

    public Empleado(String nombre, String apellido, String dni, String legajo) {
        super(nombre, apellido, dni);
        this.legajo = legajo;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public void agregarStock(Producto producto, int cantidad) {
        producto.agregarStock(cantidad);
    }

    public List<Producto> filtrarProductos(Filtro filtro){
        return super.filtrarProductos(filtro);
    }

}

