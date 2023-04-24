package com.supermercado.persona;

import com.supermercado.compra.Compra;
import com.supermercado.dao.ProductoDAO;
import com.supermercado.filtro.Filtro;
import com.supermercado.productos.Producto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_persona", referencedColumnName = "id")
public class Cliente extends Persona {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> historialCompras = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String dni, List<Compra> historialCompras) {
        super(nombre, apellido, dni);
        this.historialCompras = historialCompras;
    }

    public Cliente(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public String getNombre() {
        return super.getNombre();
    }

    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    public String getApellido() {
        return super.getApellido();
    }

    public void setApellido(String apellido) {
        super.setApellido(apellido);
    }

    public String getADni() {
        return super.getDni();
    }

    public void setDni(String dni) {
        super.setDni(dni);
    }

    public List<Compra> getHistorialCompras() {

        List<Compra> aux = new ArrayList<>();
        for(Compra c : historialCompras){
            aux.add(c);
        }
        return aux;
    }

    public void setHistorialCompras(List<Compra> historialCompras) {
        this.historialCompras = historialCompras;
    }

    public void agregarCompra(Compra compra) {
        historialCompras.add(compra);
        compra.setCliente(this);
    }

    public List<Producto> filtrarProductos(Filtro filtro){
        return super.filtrarProductos(filtro);
    }
}

