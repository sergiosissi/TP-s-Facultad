package com.supermercado.compra;

import com.supermercado.persona.Cliente;
import com.supermercado.productos.Producto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "compra_producto",
            joinColumns = {@JoinColumn(name = "compra_id")},
            inverseJoinColumns = {@JoinColumn(name = "producto_id")})
    private List<Producto> productos;

    private double total;

    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Compra() {
    }

    public Compra(Cliente cliente, List<Producto> productos, double total, LocalDate fechaCompra, Pago pago) {
        this.cliente = cliente;
        this.productos = productos;
        this.total = total;
        this.fechaCompra = fechaCompra;
        this.pago = pago;
    }


    // getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        List<Producto> aux = new ArrayList<>();
        for(Producto p:this.productos){
            aux.add(p);
        }
        return aux;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}

