package com.supermercado.compra;

import com.supermercado.productos.*;

import javax.persistence.*;

@Entity
@Table(name = "compra_producto")
public class CompraProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne/*(cascade = CascadeType.REMOVE)*/
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }



}
