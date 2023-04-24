package com.supermercado.frame;

import com.supermercado.dao.ProductoDAO;
import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoCompuesto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class AgregarCombosFrame extends JFrame {
    private List<Producto> listaProductos; // Lista de todos los productos almacenados en la base de datos
    private List<Producto> listaProductosCombo; // Lista de productos que va almacenando el usuario
    private JList<Producto> jListProductos;
    private JList<Producto> jListProductosCombo;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnConfirmar;
    private JTextField txtNombreCombo;
    private JTextField txtStockInicial;
    private ProductoDAO productoDAO;

    public AgregarCombosFrame() {
        // Inicialización de la lista de productos y la lista de productos del combo
        listaProductos = new ArrayList<>();
        listaProductosCombo = new ArrayList<>();
        productoDAO = new ProductoDAO();

        // Configuración de la ventana
        setTitle("Agregar Combo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar ventana en pantalla
        setLayout(new BorderLayout());

        // Configuración del panel de la lista de productos
        JPanel panelListaProductos = new JPanel(new BorderLayout());
        panelListaProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblListaProductos = new JLabel("Productos Disponibles:");
        panelListaProductos.add(lblListaProductos, BorderLayout.NORTH);

        // Obtener la lista de todos los productos almacenados en la base de datos
        listaProductos = productoDAO.getAll();

        // Configuración del JList para mostrar los productos
        jListProductos = new JList<>(listaProductos.toArray(new Producto[0]));
        JScrollPane scrollPaneProductos = new JScrollPane(jListProductos);
        panelListaProductos.add(scrollPaneProductos, BorderLayout.CENTER);

        // Configuración del panel de la lista de productos del combo
        JPanel panelListaProductosCombo = new JPanel(new BorderLayout());
        panelListaProductosCombo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblListaProductosCombo = new JLabel("Productos del Combo:");
        panelListaProductosCombo.add(lblListaProductosCombo, BorderLayout.NORTH);

        // Configuración del JList para mostrar los productos del combo
        jListProductosCombo = new JList<>(listaProductosCombo.toArray(new Producto[0]));
        JScrollPane scrollPaneProductosCombo = new JScrollPane(jListProductosCombo);
        panelListaProductosCombo.add(scrollPaneProductosCombo, BorderLayout.CENTER);

        // Configuración del panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 5, 5));

        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el producto seleccionado en la lista de productos
                Producto productoSeleccionado = jListProductos.getSelectedValue();
                if (productoSeleccionado != null) {
                    // Agregar el producto seleccionado a la lista de productos del combo
                    listaProductosCombo.add(productoSeleccionado);
                    // Actualizar la lista de productos del combo en el JList
                    jListProductosCombo.setListData(listaProductosCombo.toArray(new Producto[0]));
                }
            }
        });
        panelBotones.add(btnAgregar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el producto seleccionado en la lista de productos del combo
                Producto productoSeleccionado = jListProductosCombo.getSelectedValue();
                if (productoSeleccionado != null) {
                    // Eliminar el producto seleccionado de la lista de productos del combo
                    listaProductosCombo.remove(productoSeleccionado);
                    // Actualizar la lista de productos del combo en el JList
                    jListProductosCombo.setListData(listaProductosCombo.toArray(new Producto[0]));
                }
            }
        });
        panelBotones.add(btnEliminar);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el nombre y el stock inicial del nuevo combo
                String nombreCombo = txtNombreCombo.getText();
                int stockInicial = Integer.parseInt(txtStockInicial.getText());

                // Crear un nuevo ProductoCompuesto con los productos del combo y el stock inicial
                //ProductoCompuesto nuevoCombo = new ProductoCompuesto(nombreCombo, stockInicial, "Combos", listaProductosCombo);
                ProductoCompuesto nuevoCombo = new ProductoCompuesto();
                nuevoCombo.setNombre(nombreCombo);
                nuevoCombo.setDepartamento("Combos");
                nuevoCombo.setStock(stockInicial);
                for(Producto p : listaProductosCombo){
                    nuevoCombo.addProducto(p);
                }

                // Agregar el nuevo combo a la base de datos
                productoDAO.guardar(nuevoCombo);

            }
        });
        panelBotones.add(btnConfirmar);

        // Configuración del panel de nombre del nuevo combo y stock inicial
        JPanel panelDatosCombo = new JPanel(new GridLayout(2, 2, 5, 5));
        panelDatosCombo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNombreCombo = new JLabel("Nombre del nuevo combo:");
        panelDatosCombo.add(lblNombreCombo);

        txtNombreCombo = new JTextField();
        panelDatosCombo.add(txtNombreCombo);

        JLabel lblStockInicial = new JLabel("Stock inicial del nuevo combo:");
        panelDatosCombo.add(lblStockInicial);

        txtStockInicial = new JTextField();
        panelDatosCombo.add(txtStockInicial);

        // Configuración del panel principal
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 3, 5, 5));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelPrincipal.add(panelListaProductos);
        panelPrincipal.add(panelListaProductosCombo);
        panelPrincipal.add(panelBotones);

        // Agregar los componentes al JFrame
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelDatosCombo, BorderLayout.SOUTH);
    }
}
