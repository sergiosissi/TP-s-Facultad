package com.supermercado.productos;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto_compuesto")
@PrimaryKeyJoinColumn(name = "producto_id", referencedColumnName = "id")
public class ProductoCompuesto extends Producto {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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


    @Override
    public String toString() {
        // Devuelve una cadena formateada con la información deseada
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(getNombre()).append(", Productos: ");
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            sb.append(producto.getNombre());
            if (i < productos.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(", Precio: ").append(getPrecio()).append(" $, Departamento: ").append(getDepartamento());
        return sb.toString();
    }



}
