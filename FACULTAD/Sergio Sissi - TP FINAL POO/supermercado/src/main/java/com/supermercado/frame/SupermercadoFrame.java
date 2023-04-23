package com.supermercado.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupermercadoFrame extends JFrame {

    private JButton btnRegistrarPersona;
    private JButton btnRealizarCompra;
    private JButton btnVerCombos;
    private JButton btnVerHistorialCompras;
    private JButton btnAgregarProducto;
    private JButton btnAgregarCombo;
    private JButton btnAgregarStock;
    private JButton btnEliminarProducto;
    private JButton btnSalir;

    public SupermercadoFrame() {
        initComponents();
        setLayout(new FlowLayout());
        add(btnRegistrarPersona);
        add(btnRealizarCompra);
        add(btnVerCombos);
        add(btnVerHistorialCompras);
        add(btnAgregarProducto);
        add(btnAgregarCombo);
        add(btnAgregarStock);
        add(btnEliminarProducto);
        add(btnSalir);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Supermercado");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        btnRegistrarPersona = new JButton("Registrar Persona");
        btnRegistrarPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarNuevaPersonaFrame registrarNuevaPersonaFrame = new RegistrarNuevaPersonaFrame();
                registrarNuevaPersonaFrame.setVisible(true);
            }
        });

        btnRealizarCompra = new JButton("Realizar Compra");
        btnRealizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RealizarCompraFrame realizarCompraFrame = new RealizarCompraFrame();
                realizarCompraFrame.setVisible(true);
            }
        });

        btnVerCombos = new JButton("Ver Combos");
        btnVerCombos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerCombosFrame verCombosFrame = new VerCombosFrame();
                verCombosFrame.setVisible(true);
            }
        });

        btnVerHistorialCompras = new JButton("Ver Historial de Compras");
        btnVerHistorialCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerHistorialComprasFrame verHistorialComprasFrame = new VerHistorialComprasFrame();
                verHistorialComprasFrame.setVisible(true);
            }
        });


        btnRealizarCompra = new JButton("Realizar Compra");
        btnRealizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RealizarCompraFrame realizarCompraFrameFrame = new RealizarCompraFrame();
                realizarCompraFrameFrame.setVisible(true);
            }
        });

        btnAgregarProducto = new JButton("Agregar Productos");
        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarProductosFrame agregarProductosFrame = new AgregarProductosFrame();
                agregarProductosFrame.setVisible(true);
            }
        });

        btnAgregarCombo = new JButton("Agregar Combos");
        btnAgregarCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarCombosFrame agregarCombosFrame = new AgregarCombosFrame();
                agregarCombosFrame.setVisible(true);
            }
        });

        btnAgregarStock = new JButton("Agregar Stock");
        btnAgregarStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarStockFrame agregarStockFrame = new AgregarStockFrame();
                agregarStockFrame.setVisible(true);
            }
        });

        btnEliminarProducto = new JButton("Eliminar Producto");
        btnEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarProductoFrame eliminarProductoFrame = new EliminarProductoFrame();
                eliminarProductoFrame.setVisible(true);
            }
        });

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // salir de la aplicacion
                System.exit(0);
            }
        });
    }
}