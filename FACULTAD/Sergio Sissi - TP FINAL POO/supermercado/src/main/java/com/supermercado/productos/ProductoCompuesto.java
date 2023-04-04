package com.supermercado.productos;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto_compuesto")
@PrimaryKeyJoinColumn(name = "producto_id", referencedColumnName = "id")
public class ProductoCompuesto extends Producto {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "producto_compuesto_producto",
            joinColumns = @JoinColumn(name = "producto_compuesto_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    public ProductoCompuesto() {
        productos = new ArrayList<>();
    }

    public ProductoCompuesto(String nombre, int stock, String departamento, List<Producto> productos) {
        super(nombre, stock, departamento);
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    public void removeProducto(Producto producto) {
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
