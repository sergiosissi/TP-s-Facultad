package com.supermercado.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupermercadoFrame extends JFrame {

    private JButton btnRegistrarCliente;
    private JButton btnRealizarCompra;
    private JButton btnVerCombos;
    private JButton btnAgregarStock;
    private JButton btnSalir;

    public SupermercadoFrame() {
        initComponents();
        setLayout(new FlowLayout());
        add(btnRegistrarCliente);
        add(btnRealizarCompra);
        add(btnVerCombos);
        add(btnAgregarStock);
        add(btnSalir);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Supermercado");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        btnRegistrarCliente = new JButton("Registrar Cliente");
        btnRegistrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarClienteFrame registrarClienteFrame = new RegistrarClienteFrame();
                registrarClienteFrame.setVisible(true);
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


        btnRealizarCompra = new JButton("Realizar Compra");
        btnRealizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RealizarCompraFrame realizarCompraFrameFrame = new RealizarCompraFrame();
                realizarCompraFrameFrame.setVisible(true);
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