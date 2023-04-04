package com.supermercado.frame;

import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoCompuesto;
import com.supermercado.productos.ProductoPorPeso;
import com.supermercado.productos.ProductoSimple;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AgregarStockFrame extends JFrame {
    private JTable tablaProductos;
    private JTextField txtUnidades;
    private JButton btnAgregarUnidades;
    private DefaultTableModel modeloProductos;

    public AgregarStockFrame() {
        // Obtener los productos existentes
        List<Producto> productos =obtenerProductosDeLaBaseDeDatos();

        // Configurar la tabla de productos
        String[] columnasProductos = {"Nombre", "Departamento", "Stock", "Tipo de producto"};
        modeloProductos = new DefaultTableModel(columnasProductos, 0);
        tablaProductos = new JTable(modeloProductos);
        for (Producto producto : productos) {
            Object[] fila = {producto.getNombre(), producto.getDepartamento(), producto.getStock(),
                    producto instanceof ProductoSimple ? "Simple" :
                            producto instanceof ProductoCompuesto ? "Combo" : "Por peso"};
            modeloProductos.addRow(fila);
        }

        // Configurar el campo de texto para unidades y el botón de agregar unidades
        txtUnidades = new JTextField(10);
        btnAgregarUnidades = new JButton("Agregar unidades");
        btnAgregarUnidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaProductos.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    Producto producto = productos.get(filaSeleccionada);
                    try {
                        int unidades = Integer.parseInt(txtUnidades.getText());
                        producto.agregarStock(unidades);
                        modeloProductos.setValueAt(producto.getStock(), filaSeleccionada, 2);
                        JOptionPane.showMessageDialog(null, "Unidades agregadas exitosamente");
                        txtUnidades.setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido de unidades");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un producto de la tabla");
                }
            }
        });

        // Agregar los componentes al frame
        setLayout(new BorderLayout());
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Unidades: "));
        panelInferior.add(txtUnidades);
        panelInferior.add(btnAgregarUnidades);
        add(panelInferior, BorderLayout.SOUTH);

        // Configurar el frame
        setTitle("Agregar unidades de stock");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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
