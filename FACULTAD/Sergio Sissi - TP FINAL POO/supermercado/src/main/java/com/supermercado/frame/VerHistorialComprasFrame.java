package com.supermercado.frame;

import com.supermercado.compra.Compra;
import com.supermercado.compra.CompraProducto;
import com.supermercado.dao.ClienteDAO;
import com.supermercado.persona.Cliente;
import com.supermercado.dao.CompraDAO;
import com.supermercado.dao.CompraProductoDAO;
import com.supermercado.dao.ProductoDAO;
import com.supermercado.productos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VerHistorialComprasFrame extends JFrame {

    private Cliente cliente;
    private CompraDAO compraDAO = new CompraDAO();
    private CompraProductoDAO compraProductoDAO = new CompraProductoDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private ClienteDAO  clienteDAO = new ClienteDAO();


    private JPanel mainPanel;
    private JTable comprasTable;

    public VerHistorialComprasFrame() {

        String dni = JOptionPane.showInputDialog("Ingrese su DNI:");

        if (dni == null || dni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un CUIL válido.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        this.cliente = clienteDAO.getClienteByDNI(dni);

        if (this.cliente == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un cliente con el CUIL ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }


        initComponents();
        setContentPane(mainPanel);
        setTitle("Historial de compras");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        comprasTable = new JTable();

        JScrollPane scrollPane = new JScrollPane(comprasTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        updateComprasTable();
    }

    private void updateComprasTable() {
        String[] columnNames = {"Producto", "Precio", "Fecha"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Compra> compras = CompraDAO.getComprasByClienteId(cliente.getId());

        for (Compra compra : compras) {
            List<CompraProducto> comprasProductos = CompraProductoDAO.getComprasProductosByCompraId(compra.getId());
            for (CompraProducto compraProducto : comprasProductos) {
                Producto producto = compraProducto.getProducto();
                double precio = producto.getPrecio();
                String fecha = compra.getFechaCompra().toString();
                Object[] row = {producto.getNombre(), precio, fecha};
                model.addRow(row);
            }
            double totalCompra = compra.getTotal();
            Object[] row = {"", "Total compra: " + totalCompra, ""};
            model.addRow(row);
            Object[] spaceRow = {"", "", ""};
            model.addRow(spaceRow);
        }

        comprasTable.setModel(model);
    }
}

