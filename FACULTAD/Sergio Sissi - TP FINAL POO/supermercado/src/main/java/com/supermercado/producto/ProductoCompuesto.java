package com.supermercado.producto;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductoCompuesto extends Producto {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_compuesto_id")
    private List<Producto> productos;

    public ProductoCompuesto() {
        productos = new ArrayList<>();
    }

    public ProductoCompuesto(String nombre, int stock, String departamento, List<Producto> productos) {
        super(nombre, stock, departamento);
        this.productos = productos;
    }

    public void addProducto(ProductoSimple producto) {
        productos.add(producto);
    }

    public void removeProducto(ProductoSimple producto) {
        productos.remove(producto);
    }

    // getters y setters
    public double getPrecio() {
        double precioTotal = 0;
        for (Producto producto : productos) {
            precioTotal += producto.getPrecio();
        }
        return precioTotal;
    }

    public List<Producto> getProductos() {
        List<Producto> aux = new ArrayList<>();
        for(Producto p:this.productos){
            aux.add(p);
        }
        return aux;
    }





}
