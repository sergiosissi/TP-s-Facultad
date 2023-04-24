package com.supermercado.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.supermercado.dao.CompraDAO;
import com.supermercado.dao.CompraProductoDAO;
import com.supermercado.dao.ProductoDAO;
import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoCompuesto;
import com.supermercado.productos.ProductoPorPeso;
import com.supermercado.productos.ProductoSimple;

public class EliminarProductoFrame extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private ProductoDAO productoDao = new ProductoDAO();
    private CompraProductoDAO compraProductoDAO = new CompraProductoDAO();
    private CompraDAO compraDAO = new CompraDAO();
    private List<Producto> productos;
    private JList<Producto> listaProductos;

    /**
     * Create the dialog.
     */
    public EliminarProductoFrame() {
        // Obtener todos los productos de la base de datos
        productos = productoDao.getAll();

        setBounds(100, 100, 450, 300);
        setTitle("Eliminar producto");
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblSeleccioneUnProducto = new JLabel("Seleccione un producto:");
        lblSeleccioneUnProducto.setBounds(10, 11, 414, 14);
        contentPanel.add(lblSeleccioneUnProducto);

        // Mostrar una lista de los productos de la base de datos
        listaProductos = new JList<Producto>(productos.toArray(new Producto[0]));
        listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProductos.setBounds(10, 36, 414, 171);
        contentPanel.add(listaProductos);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Eliminar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el producto seleccionado
                Producto productoSeleccionado = listaProductos.getSelectedValue();
                if (productoSeleccionado == null) {
                    JOptionPane.showMessageDialog(EliminarProductoFrame.this, "Seleccione un producto", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Verificar si el producto es un componente de un combo
                    if (productoDao.perteneceACombo(productoSeleccionado.getId())) {
                        JOptionPane.showMessageDialog(EliminarProductoFrame.this, "Este producto es un componente de un combo y no se puede eliminar", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Eliminar el producto seleccionado de la base de datos

                        compraProductoDAO.eliminarCompraProductoByProductoId(productoSeleccionado.getId());
                        if(productoSeleccionado instanceof ProductoCompuesto){
                            productoDao.eliminarProductosComboByProductoId(productoSeleccionado.getId());
                        }else {
                            if (productoSeleccionado instanceof ProductoPorPeso) {
                                productoDao.deleteProductoPorPeso((ProductoPorPeso) productoSeleccionado);
                            }
                            productoDao.deleteProductoSimple((ProductoSimple) productoSeleccionado);
                        }
                        productoDao.deleteById(productoSeleccionado.getId());
                        JOptionPane.showMessageDialog(EliminarProductoFrame.this, "Producto eliminado correctamente",
                                "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        okButton.setActionCommand("Eliminar");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancelar");
        buttonPane.add(cancelButton);
    }

}

