package com.supermercado.frame;

import com.supermercado.dao.ProductoDAO;
import com.supermercado.filtro.FiltroStockMenor;
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

    private JButton btnFiltrar;
    private int stockFiltrado;
    private DefaultTableModel modeloProductos;
    private ProductoDAO productoDAO;

    public AgregarStockFrame() {
        // Obtener los productos existentes
        List<Producto> productos = getProductos();

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

        // Configurar el botón de filtrar
        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stockFiltrado = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el valor de stock a filtrar"));
                    filtrarProductos(stockFiltrado);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido");
                }
            }
        });

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
                        productoDAO.update(producto);
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
        panelInferior.add(btnFiltrar);
        add(panelInferior, BorderLayout.SOUTH);

        // Configurar el frame
        setTitle("Agregar unidades de stock");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private List<Producto> getProductos() {
        // En este ejemplo, simulamos obtener los datos de la base de datos
        List<Producto> productos = new ArrayList<>();
        this.productoDAO = new ProductoDAO();
        productos = this.productoDAO.getAll();

        return productos;
    }

    private void filtrarProductos(int stockFiltrado) {
        // Obtener los productos existentes
        List<Producto> productos = getProductos();

        FiltroStockMenor filtroStockMenor = new FiltroStockMenor(stockFiltrado);
        // Limpiar el modelo de la tabla
        modeloProductos.setRowCount(0);

        // Filtrar los productos por stock menor al valor especificado
        for (Producto producto : productos) {
            if (filtroStockMenor.cumple(producto)) {
                Object[] fila = {producto.getNombre(), producto.getDepartamento(), producto.getStock(),
                        producto instanceof ProductoSimple ? "Simple" :
                                producto instanceof ProductoCompuesto ? "Combo" : "Por peso"};
                modeloProductos.addRow(fila);
            }
        }
    }

}
