package com.supermercado.frame;

import com.supermercado.dao.ProductoDAO;
import com.supermercado.productos.ProductoPorPeso;
import com.supermercado.productos.ProductoSimple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarProductosFrame extends JFrame {
    private JTextField txtNombre;
    private JTextField txtStock;
    private JTextField txtDepartamento;
    private JTextField txtPrecio;
    private JTextField txtPeso;
    private JButton btnAgregar;
    private JRadioButton rdbtnProductoSimple;
    private JRadioButton rdbtnProductoPorPeso;

    private ProductoDAO productoDAO;

    public AgregarProductosFrame() {
        setTitle("Agregar Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        // Etiquetas
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblStock = new JLabel("Stock:");
        JLabel lblDepartamento = new JLabel("Departamento:");
        JLabel lblPrecio = new JLabel("Precio:");
        JLabel lblPeso = new JLabel("Peso:");

        // Campos de texto
        txtNombre = new JTextField();
        txtStock = new JTextField();
        txtDepartamento = new JTextField();
        txtPrecio = new JTextField();
        txtPeso = new JTextField();

        // Botones de radio
        rdbtnProductoSimple = new JRadioButton("Producto Simple");
        rdbtnProductoPorPeso = new JRadioButton("Producto por Peso");

        // Grupo de botones de radio
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdbtnProductoSimple);
        btnGroup.add(rdbtnProductoPorPeso);

        // Botón de agregar
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String stockStr = txtStock.getText();
                String departamento = txtDepartamento.getText();
                String precioStr = txtPrecio.getText();
                String pesoStr = txtPeso.getText();
                int stock = 0;
                double precio = 0;
                double peso = 1.0;
                productoDAO = new ProductoDAO();

                // Validar campos vacíos
                if (nombre.isEmpty() || precioStr.isEmpty() || (rdbtnProductoPorPeso.isSelected() && pesoStr.isEmpty())) {
                    JOptionPane.showMessageDialog(AgregarProductosFrame.this, "Por favor ingrese todos los campos.");
                    return;
                }

                // Validar formato de stock
                try {
                    stock = Integer.parseInt(String.valueOf(stockStr));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AgregarProductosFrame.this, "El precio debe ser un número válido.");
                    return;
                }


                // Validar formato de precio
                try {
                    precio = Double.parseDouble(precioStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AgregarProductosFrame.this, "El precio debe ser un número válido.");
                    return;
                }

                // Validar formato de peso si es Producto por Peso
                if (rdbtnProductoPorPeso.isSelected()) {
                    try {
                        peso = Double.parseDouble(pesoStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AgregarProductosFrame.this, "El peso debe ser un número válido.");
                        return;
                    }
                }

                // Verificar si el producto ya existe en la base de datos por el nombre
                if (ProductoDAO.existeProducto(nombre)) {
                    JOptionPane.showMessageDialog(AgregarProductosFrame.this, "El producto ya existe en la base de datos.");
                    return;
                }

                // Crear y agregar el producto a la base de datos
                if (rdbtnProductoSimple.isSelected()) {
                    ProductoSimple productoSimple = new ProductoSimple(nombre, precio, stock, departamento);
                    ProductoDAO.guardar(productoSimple);
                } else if (rdbtnProductoPorPeso.isSelected()) {
                    ProductoPorPeso productoPorPeso = new ProductoPorPeso(nombre, precio, stock, peso, departamento);
                    ProductoDAO.guardar(productoPorPeso);
                }

                JOptionPane.showMessageDialog(AgregarProductosFrame.this, "El producto ha sido agregado exitosamente.");
                // Limpiar campos de texto después de agregar el producto
                txtNombre.setText("");
                txtStock.setText("");
                txtDepartamento.setText("");
                txtPrecio.setText("");
                txtPeso.setText("");
            }
        });

        // Agregar componentes al JFrame
        add(lblNombre);
        add(txtNombre);
        add(lblStock);
        add(txtStock);
        add(lblDepartamento);
        add(txtDepartamento);
        add(lblPrecio);
        add(txtPrecio);
        add(lblPeso);
        add(txtPeso);
        add(rdbtnProductoSimple);
        add(rdbtnProductoPorPeso);
        add(new JLabel());
        add(btnAgregar);

        // Configurar tamaño y visibilidad del JFrame
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

