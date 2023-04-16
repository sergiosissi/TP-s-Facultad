package com.supermercado.frame;

import com.supermercado.dao.ProductoCompuestoDAO;
import com.supermercado.dao.ProductoCompuestoProductoDAO;
import com.supermercado.dao.ProductoDAO;
import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoCompuesto;
import com.supermercado.productos.ProductoCompuestoProducto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AgregarCombosFrame extends JFrame {
    private List<Producto> listaProductos; // Lista de productos almacenados en la base de datos
    private List<ProductoCompuesto> listaCombos; // Lista de combos agregados
    private DefaultListModel<ProductoCompuesto> modeloListaCombos; // Modelo para la lista de combos
    private JList<ProductoCompuesto> listaCombosJList; // Lista de combos en forma de JList

    private List<Producto> listaProductosSeleccionados; // Lista de productos seleccionados

    private DefaultListModel<Producto> modeloListaProductos; // Modelo para la lista de productos
    private JList<Producto> listaProductosJList; // Lista de productos en forma de JList
    private JTextField txtNombreCombo; // Cuadro de texto para el nombre del combo
    private JTextField txtStockInicial; // Cuadro de texto para el stock inicial del combo
    private JButton btnAgregarCombo; // Botón de agregar combo
    private JButton btnEliminarCombo; // Botón de eliminar combo
    private JButton btnConfirmar; // Botón de confirmar

    public AgregarCombosFrame() {
        setTitle("Agregar Combos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar listas y modelos
        ProductoDAO productoDAO = new ProductoDAO();
        listaProductos = productoDAO.getAll(); // Obtener productos desde la base de datos
        listaCombos = new ArrayList<>();
        modeloListaProductos = new DefaultListModel<>();
        for (Producto producto : listaProductos) {
            modeloListaProductos.addElement(producto);
        }
        modeloListaCombos = new DefaultListModel<>();
        listaCombosJList = new JList<>(modeloListaCombos);
        listaProductosJList = new JList<>(modeloListaProductos);

        // Configurar layout y componentes
        setLayout(new BorderLayout()); // Cambiar el layout a BorderLayout

        // Panel central para los botones y cuadros de texto
        JPanel panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER); // Agregar panel central en el centro del panel principal
        JPanel panelIzquierdo = new JPanel(new BorderLayout()); // Panel para la lista de productos y el cuadro de texto
        JPanel panelDerecho = new JPanel(new BorderLayout()); // Panel para la lista de combos

        panelIzquierdo.add(new JScrollPane(listaProductosJList), BorderLayout.CENTER); // Agregar lista de productos en el centro del panel izquierdo

        // Panel para los botones y cuadros de texto
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 5, 5)); // Panel para los botones y cuadros de texto, con espaciado entre componentes
        txtNombreCombo = new JTextField(); // Crear un nuevo cuadro de texto para el nombre del combo
        panelBotones.add(new JLabel("Nombre del Combo:")); // Etiqueta para el cuadro de texto del nombre del combo
        panelBotones.add(txtNombreCombo); // Agregar cuadro de texto al panel de botones

        txtStockInicial = new JTextField(); // Crear un nuevo cuadro de texto para el stock inicial
        panelBotones.add(new JLabel("Stock Inicial:")); // Etiqueta para el cuadro de texto del stock inicial
        panelBotones.add(txtStockInicial); // Agregar cuadro de texto al panel de botones

        btnAgregarCombo = new JButton("Agregar Combo"); // Crear un nuevo botón de agregar combo
        btnEliminarCombo = new JButton("Eliminar Combo"); // Crear un nuevo botón de eliminar combo
        btnConfirmar = new JButton("Confirmar"); // Crear un nuevo botón de confirmar


        panelBotones.add(btnAgregarCombo); // Agregar botón de agregar combo al panel de botones
        panelBotones.add(btnEliminarCombo); // Agregar botón de eliminar combo al panel de botones
        panelBotones.add(btnConfirmar); // Agregar botón de confirmar al panel de botones

        panelCentral.add(panelIzquierdo, BorderLayout.WEST); // Agregar panel izquierdo al panel central en la posición oeste
        panelCentral.add(panelBotones, BorderLayout.CENTER); // Agregar panel de botones al panel central en el centro
        panelCentral.add(panelDerecho, BorderLayout.EAST); // Agregar panel derecho al panel central en la posición este

        listaCombosJList.setBorder(BorderFactory.createTitledBorder("Combos")); // Agregar borde y título a la lista de combos
        panelDerecho.add(new JScrollPane(listaCombosJList), BorderLayout.CENTER); // Agregar lista de combos en el centro del panel derecho

        // Agregar listeners a los botones
        btnAgregarCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCombo(); // Método para agregar un combo cuando se presiona el botón de agregar combo
            }
        });

        btnEliminarCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCombo(); // Método para eliminar un combo cuando se presiona el botón de eliminar combo
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para confirmar el combo
                confirmarCombo();
            }
        });


        // Configurar el tamaño de la ventana
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true); // Hacer visible la ventana
    }

    private void agregarCombo() {
        // Obtener el nombre del combo y el stock inicial desde los cuadros de texto
        String nombreCombo = txtNombreCombo.getText();
        int stockInicial = Integer.parseInt(txtStockInicial.getText());

        // Obtener los productos seleccionados de la lista de productos
        List<Producto> productosSeleccionados = listaProductosJList.getSelectedValuesList();

        // Crear un nuevo producto compuesto con los productos seleccionados
        ProductoCompuesto combo = new ProductoCompuesto(nombreCombo, stockInicial,"Combos",productosSeleccionados);


        // Agregar el combo a la lista de combos
        listaCombos.add(combo);
        modeloListaCombos.addElement(combo);

        // Limpiar los cuadros de texto y deseleccionar los productos en la lista de productos
        txtNombreCombo.setText("");
        txtStockInicial.setText("");
        listaProductosJList.clearSelection();
    }

    private void eliminarCombo() {
        // Obtener el combo seleccionado en la lista de combos
        ProductoCompuesto comboSeleccionado = listaCombosJList.getSelectedValue();

        // Remover el combo de la lista de combos y del modelo de lista de combos
        listaCombos.remove(comboSeleccionado);
        modeloListaCombos.removeElement(comboSeleccionado);
    }

    public void confirmarCombo() {

        // Obtener nombre del combo desde el cuadro de texto
        String nombreCombo = txtNombreCombo.getText();

        // Obtener stock inicial desde el cuadro de texto
        int stockInicial = Integer.parseInt(txtStockInicial.getText());

        // Obtener los productos desde la lista
        List<Producto> productosSeleccionados = listaProductosJList.getSelectedValuesList();

        ProductoCompuesto combo = new ProductoCompuesto(nombreCombo, stockInicial, "Combos",productosSeleccionados);

            // Insertar el nuevo combo en la tabla producto_compuesto
            ProductoCompuestoDAO productoCompuestoDAO = new ProductoCompuestoDAO();
            productoCompuestoDAO.guardar(combo);

            // Insertar los productos y la relación con el combo en la tabla producto_compuesto_producto

                ProductoCompuestoProductoDAO productoCompuestoProductoDAO = new ProductoCompuestoProductoDAO();
                productoCompuestoProductoDAO.guardar(combo, productosSeleccionados);


            // Insertar el nuevo combo en la tabla producto
            ProductoDAO productoDAO = new ProductoDAO();
            productoDAO.guardar(combo);

    }


}

