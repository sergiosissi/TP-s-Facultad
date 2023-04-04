package com.supermercado.frame;

import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoCompuesto;
import com.supermercado.productos.ProductoPorPeso;
import com.supermercado.productos.ProductoSimple;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RealizarCompraFrame extends JFrame {

    private JTable tablaProductos;
    private JTable tablaCarrito;
    private JButton btnConfirmarCompra;
    private JLabel lblTotal;
    private List<Producto> carrito;
    private double total;

    public RealizarCompraFrame() {
        super("Realizar Compra");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        // Obtener datos de la base de datos
        List<Producto> productos = obtenerProductosDeLaBaseDeDatos();

        // Crear tabla de productos
        String[] columnasProductos = {"Nombre", "Departamento", "Precio"};
        DefaultTableModel modeloProductos = new DefaultTableModel(columnasProductos, 0);
        for (Producto producto : productos) {
            Object[] fila = {producto.getNombre(), producto.getDepartamento(), producto.getPrecio()};
            modeloProductos.addRow(fila);
        }
        tablaProductos = new JTable(modeloProductos);

        // Crear tabla de carrito
        String[] columnasCarrito = {"Nombre", "Departamento", "Precio", "Tipo de producto"};
        DefaultTableModel modeloCarrito = new DefaultTableModel(columnasCarrito, 0);
        tablaCarrito = new JTable(modeloCarrito);

        // Crear botón de confirmar compra
        btnConfirmarCompra = new JButton("Confirmar Compra");
        btnConfirmarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realizar la compra y cerrar la ventana
                realizarCompra();
                dispose();
            }
        });

        // Crear etiqueta para el total
        lblTotal = new JLabel("Total: $0.00");

        // Crear paneles
        JPanel panelProductos = new JPanel(new BorderLayout());
        panelProductos.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        JPanel panelCarrito = new JPanel(new BorderLayout());
        panelCarrito.add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar al Carrito");
        JButton btnEliminar = new JButton("Eliminar del Carrito");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.add(lblTotal, BorderLayout.CENTER);
        panelTotal.add(btnConfirmarCompra, BorderLayout.EAST);

        // Agregar paneles a la ventana
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelProductos, panelCarrito);
        splitPane.setResizeWeight(0.5);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        getContentPane().add(panelBotones, BorderLayout.SOUTH);
        getContentPane().add(panelTotal, BorderLayout.NORTH);

        // Inicializar lista de compras
        carrito = new ArrayList<>();

        // Configurar botón de agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar el producto seleccionado al carrito
                int filaSeleccionada = tablaProductos.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    Producto producto = productos.get(filaSeleccionada);
                    if (producto instanceof ProductoPorPeso) {
                        String pesoStr = JOptionPane.showInputDialog("Ingrese el peso del producto:");
                        double peso = Double.parseDouble(pesoStr);
                        ((ProductoPorPeso) producto).setPeso(peso);
                    }
                    carrito.add(producto);
                    Object[] fila = {producto.getNombre(), producto.getDepartamento(), producto.getPrecio(),
                            producto instanceof ProductoSimple ? "Simple" :
                                    producto instanceof ProductoCompuesto ? "Combo" : "Por peso"};
                    modeloCarrito.addRow(fila);
                    total += producto.getPrecio();
                    actualizarTotal();
                }
            }
        });

        // Configurar botón de eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Eliminar el producto seleccionado del carrito
                int filaSeleccionada = tablaCarrito.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    Producto producto = carrito.get(filaSeleccionada);
                    carrito.remove(producto);
                    modeloCarrito.removeRow(filaSeleccionada);
                    total -= producto.getPrecio();
                    actualizarTotal();
                }
            }
        });
    }



    private void realizarCompra() {
        // Realizar la compra, por ejemplo, guardando los datos en la base de datos
        // En este ejemplo, simplemente imprimimos los productos comprados y el total
        System.out.println("Productos comprados:");
        for (Producto producto : carrito) {
            System.out.println(producto.getNombre() + " - " + producto.getDepartamento() + " - $" + producto.getPrecio());
        }
        System.out.println("Total: $" + total);
    }

    private void actualizarTotal() {
        lblTotal.setText("Total: $" + String.format("%.2f", total));
    }

    private List<Producto> obtenerProductosDeLaBaseDeDatos() {
        // En este ejemplo, simulamos obtener los datos de la base de datos
        List<Producto> productos = new ArrayList<>();
        productos.add(new ProductoPorPeso("Manzanas", 2.50, 20, 1, "Frutas" ));
        productos.add(new ProductoSimple("Leche", 100.00, 30, "Lácteos"));
        productos.add(new ProductoSimple("Pan", 30.00, 20,  "Panadería"));
        productos.add(new ProductoSimple("Queso",3.00, 34, "Lácteos" ));
        productos.add(new ProductoSimple("Tomates", 10.50, 30, "Verduras"));

        ProductoSimple lechuga = new ProductoSimple("Lechuga", 30, 30, "Verduras");
        ProductoSimple rabanito = new ProductoSimple("Rabanito", 25, 30, "Verduras");
        List<Producto> comboEnsalada = new ArrayList<>();
        comboEnsalada.add(lechuga);
        comboEnsalada.add(rabanito);
        productos.add(new ProductoCompuesto("Combo Ensalada", 3, "Combos", comboEnsalada));
        return productos;
    }

}
