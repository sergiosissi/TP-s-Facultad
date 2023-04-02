package com.supermercado.persona;

import com.supermercado.compra.Compra;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Persona {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> historialCompras = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String dni, String telefono, List<Compra> historialCompras) {
        super(nombre, apellido, dni, telefono);
        this.historialCompras = historialCompras;
    }

    public Cliente(String nombre, String apellido, String dni, String telefono) {
        super(nombre, apellido, dni, telefono);
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

    public String getATelefono() {
        return super.getTelefono();
    }

    public void setTelefono(String telefono) {
        super.setTelefono(telefono);
    }

    public List<Compra> getHistorialCompras() {
        return historialCompras;
    }

    public void setHistorialCompras(List<Compra> historialCompras) {
        this.historialCompras = historialCompras;
    }

    public void agregarCompra(Compra compra) {
        historialCompras.add(compra);
        compra.setCliente(this);
    }
}

