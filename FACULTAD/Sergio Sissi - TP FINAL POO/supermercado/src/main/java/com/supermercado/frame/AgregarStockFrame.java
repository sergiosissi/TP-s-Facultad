package com.supermercado.frame;

import com.supermercado.dao.ClienteDAO;
import com.supermercado.dao.EmpleadoDAO;
import com.supermercado.dao.ProductoDAO;
import com.supermercado.filtro.FiltroStockMenor;
import com.supermercado.persona.Empleado;
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

    private Empleado empleado;

    public AgregarStockFrame() {



        String legajo = JOptionPane.showInputDialog(null, "Ingrese su LEGAJO:", "Verificar Empleado", JOptionPane.PLAIN_MESSAGE);

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        empleado = empleadoDAO.getClienteByLegajo(legajo);

        if(empleado != null){

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
                        FiltroStockMenor filtroStockMenor = new FiltroStockMenor(stockFiltrado);
                        List<Producto> productosFiltrados = empleado.filtrarProductos(filtroStockMenor);
                        actualizarInterfazUsuario(productosFiltrados);
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
                            empleado.agregarStock(producto, unidades);
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

        }else {
            JOptionPane.showMessageDialog(AgregarStockFrame.this, "El empleado no existe. Por favor registrese para poder agregar stock", "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
        }


    }

    private List<Producto> getProductos() {
        // En este ejemplo, simulamos obtener los datos de la base de datos
        List<Producto> productos = new ArrayList<>();
        this.productoDAO = new ProductoDAO();
        productos = this.productoDAO.getAll();

        return productos;
    }
    
    public void actualizarInterfazUsuario(List<Producto> productosFiltrados) {
        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaProductos.getModel();
        // Limpiar la tabla
        modeloTabla.setRowCount(0);

        // Actualizar la tabla con los productos filtrados
        for (Producto producto : productosFiltrados) {
            // Crear un arreglo con los datos del producto
            Object[] fila = {producto.getNombre(), producto.getDepartamento(), producto.getStock(),
                    producto instanceof ProductoSimple ? "Simple" :
                            producto instanceof ProductoCompuesto ? "Combo" : "Por peso"};
            // Agregar la fila a la tabla
            modeloTabla.addRow(fila);
        }
    }

}
