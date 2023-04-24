package com.supermercado.frame;

import com.supermercado.dao.ClienteDAO;
import com.supermercado.dao.ProductoDAO;
import com.supermercado.filtro.*;
import com.supermercado.persona.Cliente;
import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoCompuesto;
import com.supermercado.productos.ProductoPorPeso;
import com.supermercado.productos.ProductoSimple;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RealizarCompraFrame extends JFrame {

    private JTable tablaProductos;
    private JTable tablaCarrito;
    private JButton btnConfirmarCompra;
    private JLabel lblTotal;
    private JButton btnFiltrar;
    private Cliente cliente;
    private List<Producto> carrito;
    private List<Producto> productos;

    private ProductoDAO productoDAO;
    private double total;

    public RealizarCompraFrame() {





        String dni = JOptionPane.showInputDialog(null, "Ingrese su DNI:", "Verificar Cliente", JOptionPane.PLAIN_MESSAGE);

        ClienteDAO clienteDAO = new ClienteDAO();

        cliente = clienteDAO.getClienteByDNI(dni);

        if(cliente != null){

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(600, 400);

            this.setVisible(true);

            // Obtener datos de la base de datos
            productoDAO = new ProductoDAO();
            productos = getProductos();



            // Crear tabla de productos
            String[] columnasProductos = {"Nombre", "Departamento", "Precio"};
            DefaultTableModel modeloProductos = new DefaultTableModel(columnasProductos, 0);
            for (Producto producto : productos) {
                Object[] fila = {producto.getNombre(), producto.getDepartamento(), producto.getPrecio()};
                modeloProductos.addRow(fila);
            }
            tablaProductos = new JTable(modeloProductos);

            // Crear tabla de carrito
            String[] columnasCarrito = {"Nombre", "Departamento", "Precio", "Tipo de producto"};
            DefaultTableModel modeloCarrito = new DefaultTableModel(columnasCarrito, 0);
            tablaCarrito = new JTable(modeloCarrito);


            //Crear botón para filtrar los productos

            btnFiltrar = new JButton("Filtrar");
            btnFiltrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Crear y mostrar la ventana de FiltrosFrame
                    FiltrosFrame filtrosFrame = new FiltrosFrame(RealizarCompraFrame.this);
                }
            });

            // Crear botón de confirmar compra
            btnConfirmarCompra = new JButton("Confirmar Compra");
            btnConfirmarCompra.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Realizar la compra
                    if(carrito != null && !carrito.isEmpty()){

                        for (Producto producto : carrito) {
                            int nuevoStock = producto.getStock() - 1;
                            producto.setStock(nuevoStock);
                            // Actualizar el stock en la base de datos
                            productoDAO.update(producto);
                        }

                        MetodoPagoFrame metodoPagoFrame = new MetodoPagoFrame(cliente.getId(), carrito, total);
                        metodoPagoFrame.setVisible(true);

                    }else{
                        JOptionPane.showInputDialog("No tiene productos en el carrito de compra");
                    }

                    dispose();
                }
            });

            // Crear etiqueta para el total
            lblTotal = new JLabel("Total: $0.00");

            // Crear paneles
            JPanel panelProductos = new JPanel(new BorderLayout());
            panelProductos.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

            JPanel panelCarrito = new JPanel(new BorderLayout());
            panelCarrito.add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);

            JPanel panelBotones = new JPanel();
            JButton btnAgregar = new JButton("Agregar al Carrito");
            JButton btnEliminar = new JButton("Eliminar del Carrito");
            panelBotones.add(btnAgregar);
            panelBotones.add(btnEliminar);

            JPanel panelTotal = new JPanel(new BorderLayout());
            panelTotal.add(lblTotal, BorderLayout.CENTER);
            panelTotal.add(btnConfirmarCompra, BorderLayout.EAST);
            panelTotal.add(btnFiltrar, BorderLayout.NORTH);

            // Agregar paneles a la ventana
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelProductos, panelCarrito);
            splitPane.setResizeWeight(0.5);
            getContentPane().add(splitPane, BorderLayout.CENTER);

            getContentPane().add(panelBotones, BorderLayout.SOUTH);
            getContentPane().add(panelTotal, BorderLayout.NORTH);

            // Inicializar lista de compras
            carrito = new ArrayList<>();

            // Configurar botón de agregar
            btnAgregar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Agregar el producto seleccionado al carrito
                    int filaSeleccionada = tablaProductos.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        Producto producto = productos.get(filaSeleccionada);
                        if (producto instanceof ProductoPorPeso) {
                            String pesoStr = JOptionPane.showInputDialog("Ingrese el peso del producto:");
                            double peso = Double.parseDouble(pesoStr);
                            ((ProductoPorPeso) producto).setPeso(peso);
                        }
                        carrito.add(producto);
                        Object[] fila = {producto.getNombre(), producto.getDepartamento(), producto.getPrecio(),
                                producto instanceof ProductoSimple ? "Simple" :
                                        producto instanceof ProductoCompuesto ? "Combo" : "Por peso"};
                        modeloCarrito.addRow(fila);
                        total += producto.getPrecio();
                        actualizarTotal();
                    }
                }
            });

            // Configurar botón de eliminar
            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Eliminar el producto seleccionado del carrito
                    int filaSeleccionada = tablaCarrito.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        Producto producto = carrito.get(filaSeleccionada);
                        carrito.remove(producto);
                        modeloCarrito.removeRow(filaSeleccionada);
                        total -= producto.getPrecio();
                        actualizarTotal();
                    }
                }
            });
        }else{
            JOptionPane.showMessageDialog(RealizarCompraFrame.this, "El cliente no existe. Por favor registrese para poder comprar", "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
        }

    }



    private void realizarCompra() {


        System.out.println("Productos comprados:");
        for (Producto producto : carrito) {
            System.out.println(producto.getNombre() + " - " + producto.getDepartamento() + " - $" + producto.getPrecio());
        }
        System.out.println("Total: $" + total);
    }

    private void actualizarTotal() {
        lblTotal.setText("Total: $" + String.format("%.2f", total));
    }

    private List<Producto> getProductos() {

        return productoDAO.getAll();
    }


    public void actualizarInterfazUsuario(List<Producto> productosFiltrados) {
        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaProductos.getModel();
        // Limpiar la tabla
        modeloTabla.setRowCount(0);

        // Actualizar la tabla con los productos filtrados
        for (Producto producto : productosFiltrados) {
            // Crear un arreglo con los datos del producto
            Object[] fila = {producto.getNombre(), producto.getDepartamento(), producto.getPrecio()};
            // Agregar la fila a la tabla
            modeloTabla.addRow(fila);
        }
    }

    public void aplicarFiltro(Filtro f){

        List<Producto> productosFiltrados =  cliente.filtrarProductos(f);
        actualizarInterfazUsuario(productosFiltrados);

    }
}
