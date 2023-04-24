package com.supermercado.persona;

import com.supermercado.dao.ProductoDAO;
import com.supermercado.filtro.Filtro;
import com.supermercado.productos.Producto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private String dni;


    public Persona(String nombre, String apellido, String dni) {
    }

    public Persona() {
    }
// Getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Producto> filtrarProductos(Filtro filtro){

        List<Producto> productos = ProductoDAO.getAll();
        List<Producto> productosFiltrados = new ArrayList<>();

        for(Producto p : productos){
            if(filtro.cumple(p)){
                productosFiltrados.add(p);
            }
        }

        return productosFiltrados;

    }


}


