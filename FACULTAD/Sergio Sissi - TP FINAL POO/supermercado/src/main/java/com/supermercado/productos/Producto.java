package com.supermercado.productos;

import javax.persistence.*;

@Entity
@Table(name = "producto")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "nombre")
    protected String nombre;

    @Column(name = "stock")
    protected int stock;

    @Column(name = "departamento")
    private String departamento;


    public Producto() {}

    public Producto(String nombre, int stock, String departamento) {
        this.nombre = nombre;
        this.stock = stock;
        this.departamento = departamento;
    }

    public abstract double getPrecio();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void aumentarStock(int cantidad) {
        this.stock+=cantidad;
    }


    public void agregarStock(int cantidad) {
        this.stock += cantidad;
    }
}

