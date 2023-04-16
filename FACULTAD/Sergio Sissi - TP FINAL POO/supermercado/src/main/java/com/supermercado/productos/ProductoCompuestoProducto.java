package com.supermercado.productos;

import javax.persistence.*;

@Entity
@Table(name = "producto_compuesto_producto")
public class ProductoCompuestoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_compuesto_id")
    private ProductoCompuesto productoCompuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public ProductoCompuestoProducto() {
    }

    public ProductoCompuestoProducto(ProductoCompuesto productoCompuesto, Producto producto) {
        this.productoCompuesto = productoCompuesto;
        this.producto = producto;
    }

    public void setProductoCompuesto(ProductoCompuesto productoCompuesto) {
        this.productoCompuesto = productoCompuesto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Getters y setters
}
