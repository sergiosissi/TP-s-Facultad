package com.supermercado.frame;

import com.supermercado.dao.ProductoCompuestoDAO;
import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoCompuesto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerCombosFrame extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> comboBox;
    private JTextArea textArea;

    public VerCombosFrame() {
        // Configuración de la ventana
        setTitle("Ver Combos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Creación del panel de selección de combo
        JPanel comboPanel = new JPanel();
        contentPane.add(comboPanel, BorderLayout.NORTH);
        comboPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblCombo = new JLabel("Combo:");
        comboPanel.add(lblCombo);

        comboBox = new JComboBox<>();
        comboPanel.add(comboBox);

        JButton btnMostrarProductos = new JButton("Mostrar Productos");
        comboPanel.add(btnMostrarProductos);

        // Creación del área de texto para mostrar los productos del combo seleccionado
        textArea = new JTextArea();
        contentPane.add(textArea, BorderLayout.CENTER);

        // Acción del botón "Mostrar Productos"
        btnMostrarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosComboSeleccionado();
            }
        });

        // Cargar los combos en el combo box
        cargarCombos();
    }

    private void cargarCombos() {
        // Obtener los combos de la base de datos o donde se almacenen
        ProductoCompuestoDAO productoCompuestoDAO = new ProductoCompuestoDAO();
        List<ProductoCompuesto> combos = productoCompuestoDAO.getAll();


        // Agregar los combos al combo box
        for (ProductoCompuesto combo : combos) {
            comboBox.addItem(combo.getNombre());
        }
    }

    private void mostrarProductosComboSeleccionado() {
        // Obtener el combo seleccionado en el combo box
        String nombreComboSeleccionado = (String) comboBox.getSelectedItem();

        // Obtener el combo de la base de datos o donde se almacene
        ProductoCompuesto comboSeleccionado = obtenerComboDesdeBD(nombreComboSeleccionado);

        // Obtener los productos del combo a partir de la tabla producto_compuesto_producto
        List<Producto> productos = obtenerProductosDeComboDesdeBD(comboSeleccionado.getId());

        // Mostrar los productos en el área de texto
        textArea.setText("");
        for (Producto producto : productos) {
            textArea.append(producto.getNombre() + " - " + producto.getPrecio() + "\n");
        }
    }

    private List<ProductoCompuesto> obtenerCombosDesdeBD() {
        // Implementación para obtener los combos desde la base de datos o donde se almacenen
        return null;
    }

    private ProductoCompuesto obtenerComboDesdeBD(String nombreCombo) {
        // Implementación para obtener un combo de la base de datos o donde se almacene, a partir de su nombre
        return null;
    }

    private List<Producto> obtenerProductosDeComboDesdeBD(int idCombo) {
        // Implementación para obtener los productos de un combo a partir de la tabla producto_compuesto_producto
        return null;
    }
}

