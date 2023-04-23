package com.supermercado.frame;

import com.supermercado.dao.ProductoDAO;
import com.supermercado.productos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VerCombosFrame extends JFrame {
    private List<Producto> listaCombos; // Lista de todos los combos almacenados en la base de datos
    private JTable tablaCombos;
    private JButton btnFiltrar;
    private ProductoDAO productoDao;

    public VerCombosFrame() {
        // Inicialización de la lista de combos
        listaCombos = new ArrayList<>();
        productoDao = new ProductoDAO();
        List<Producto> productosDelCombo = new ArrayList<>();

        // Configuración de la ventana
        setTitle("Ver Combos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar ventana en pantalla
        setLayout(new BorderLayout());

        // Configuración del panel de la tabla de combos
        JPanel panelTablaCombos = new JPanel(new BorderLayout());
        panelTablaCombos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblListaCombos = new JLabel("Combos Disponibles:");
        panelTablaCombos.add(lblListaCombos, BorderLayout.NORTH);

        // Obtener la lista de todos los combos almacenados en la base de datos

        listaCombos = productoDao.getAllCombos();

        // Configuración de la tabla para mostrar los combos
        String[] columnas = {"Nombre", "Precio", "Stock", "Productos"};
        DefaultTableModel modeloTablaCombos = new DefaultTableModel(columnas, 0);
        for (Producto combo : listaCombos) {
            String nombre = combo.getNombre();
            String precio = String.valueOf(combo.getPrecio());
            String stock = String.valueOf(combo.getStock());
            productosDelCombo = productoDao.getProductosByProductoCompuestoId(combo.getId());

            String productos = "";
            for(Producto pdc : productosDelCombo){
                productos += pdc.getNombre() + ", ";
            }
            productos = productos.substring(0, productos.length() - 2);
            String[] fila = {nombre, precio, stock, productos};
            modeloTablaCombos.addRow(fila);
        }
        tablaCombos = new JTable(modeloTablaCombos);
        JScrollPane scrollPaneCombos = new JScrollPane(tablaCombos);
        panelTablaCombos.add(scrollPaneCombos, BorderLayout.CENTER);

        // Configuración del panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());


        // Agregar componentes a la ventana
        add(panelTablaCombos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
