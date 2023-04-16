package com.supermercado.frame;

import com.supermercado.compra.*;
import com.supermercado.compra.Efectivo;
import com.supermercado.compra.Tarjeta;
import com.supermercado.dao.ClienteDAO;
import com.supermercado.dao.CompraDAO;
import com.supermercado.dao.CompraProductoDAO;
import com.supermercado.dao.PagoDAO;
import com.supermercado.persona.Cliente;
import com.supermercado.productos.*;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class MetodoPagoFrame extends JFrame {

    private JComboBox<String> cmbMetodoPago;
    private JComboBox<String> cmbTipoTarjeta;
    private JComboBox<Integer> cmbCuotas;
    private JButton btnFinalizarCompra;

    public MetodoPagoFrame(Long idCliente, List<Producto> carrito, double montoTotal) {

        super("Seleccionar Método de Pago");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        // Crear combo box para elegir el método de pago
        String[] metodosPago = {"Efectivo", "Tarjeta"};
        cmbMetodoPago = new JComboBox<>(metodosPago);

        // Crear combo box para elegir el tipo de tarjeta
        String[] tiposTarjeta = {"Débito", "Crédito"};
        cmbTipoTarjeta = new JComboBox<>(tiposTarjeta);
        cmbTipoTarjeta.setEnabled(false);

        // Crear combo box para elegir el número de cuotas
        Integer[] cuotas = {1, 3, 6};
        cmbCuotas = new JComboBox<>(cuotas);
        cmbCuotas.setEnabled(false);

        // Crear botón de finalizar compra
        btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.setEnabled(false);
        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el método de pago seleccionado
                String metodoPago = cmbMetodoPago.getSelectedItem().toString();

                // Obtener el tipo de tarjeta seleccionado
                String tipoTarjeta = "";
                if (metodoPago.equals("Tarjeta")) {
                    tipoTarjeta = cmbTipoTarjeta.getSelectedItem().toString();
                }

                // Obtener el número de cuotas seleccionado
                int numCuotas = 0;
                if (metodoPago.equals("Tarjeta") && tipoTarjeta.equals("Crédito")) {
                    numCuotas = (int) cmbCuotas.getSelectedItem();
                }


                // Realizar la compra y cerrar la ventana
                realizarCompra(idCliente, carrito, montoTotal, metodoPago, tipoTarjeta, numCuotas);
                dispose();
            }
        });

        // Crear paneles
        JPanel panelMetodoPago = new JPanel();
        panelMetodoPago.add(new JLabel("Método de Pago:"));
        panelMetodoPago.add(cmbMetodoPago);

        JPanel panelTipoTarjeta = new JPanel();
        panelTipoTarjeta.add(new JLabel("Tipo de Tarjeta:"));
        panelTipoTarjeta.add(cmbTipoTarjeta);

        JPanel panelCuotas = new JPanel();
        panelCuotas.add(new JLabel("Cuotas:"));
        panelCuotas.add(cmbCuotas);

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnFinalizarCompra);

        // Agregar paneles a la ventana

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelMetodoPago, BorderLayout.WEST);
        getContentPane().add(panelTipoTarjeta, BorderLayout.CENTER);
        getContentPane().add(panelCuotas, BorderLayout.EAST);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);

        // Agregar listener al combo box de método de pago
        cmbMetodoPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String metodoPago = cmbMetodoPago.getSelectedItem().toString();
                if (metodoPago.equals("Efectivo")) {
                    cmbTipoTarjeta.setEnabled(false);
                    cmbCuotas.setEnabled(false);
                    btnFinalizarCompra.setEnabled(true);
                } else if (metodoPago.equals("Tarjeta")) {
                    cmbTipoTarjeta.setEnabled(true);
                    cmbCuotas.setEnabled(false);
                    btnFinalizarCompra.setEnabled(false);
                }
            }
        });

        // Agregar listener al combo box de tipo de tarjeta
        cmbTipoTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoTarjeta = cmbTipoTarjeta.getSelectedItem().toString();
                if (tipoTarjeta.equals("Crédito")) {
                    cmbCuotas.setEnabled(true);
                } else {
                    cmbCuotas.setEnabled(false);
                    btnFinalizarCompra.setEnabled(true);
                }
            }
        });

        // Agregar listener al combo box de cuotas
        cmbCuotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFinalizarCompra.setEnabled(true);
            }
        });
    }

    private void realizarCompra(Long idCliente, List<Producto> carrito, double montoTotal, String metodoPago, String tipoTarjeta, int numCuotas) {

        PagoDAO pagoDAO = new PagoDAO();
        CompraDAO compraDAO = new CompraDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Date fecha = new Date();

        Efectivo pagoEfectivo = null;
        Tarjeta pagoTarjeta = null;

        if(metodoPago.equals("Efectivo")){
            pagoEfectivo = new Efectivo();
            pagoEfectivo.setFechaPago(fecha);
            pagoEfectivo.setDescuento(0.0);
            pagoEfectivo.setMontoPagado(montoTotal);
            PagoDAO.guardar(pagoEfectivo);

        }else{
            pagoTarjeta = new Tarjeta();
            pagoTarjeta.setTipoTarjeta(tipoTarjeta);
            pagoTarjeta.setCuotas(numCuotas);
            pagoTarjeta.setRecargo(0.0);
            pagoTarjeta.setMontoPagado(montoTotal);
            pagoTarjeta.setFechaPago(fecha);
            PagoDAO.guardar(pagoTarjeta);
        }

        Cliente cliente = clienteDAO.getClienteById(idCliente);
        Compra compra = new Compra();
        if(pagoEfectivo != null){
            compra.setFechaCompra(fecha);
            compra.setPago(pagoEfectivo);
            compra.setCliente(cliente);
            compra.setTotal(montoTotal);
            compra.setProductos(carrito);
        }else {
            compra.setFechaCompra(fecha);
            compra.setPago(pagoTarjeta);
            compra.setCliente(cliente);
            compra.setTotal(montoTotal);
            compra.setProductos(carrito);
        }
        compraDAO.guardar(compra);


        for (Producto producto : carrito) {
            CompraProducto compraProducto = new CompraProducto();
            compraProducto.setCompra(compra);
            compraProducto.setProducto(producto);

            // Guardar el registro en la tabla compra_producto
            CompraProductoDAO compraProductoDAO = new CompraProductoDAO();
            compraProductoDAO.guardar(compraProducto);
        }



    }
}

