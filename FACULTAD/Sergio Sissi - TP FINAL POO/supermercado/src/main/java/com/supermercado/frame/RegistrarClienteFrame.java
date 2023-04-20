package com.supermercado.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.compra.Compra;
import com.supermercado.dao.ClienteDAO;
import com.supermercado.dao.PersonaDAO;
import com.supermercado.persona.Cliente;
import com.supermercado.persona.Persona;

public class RegistrarClienteFrame extends JFrame {
    private JLabel nombreLabel, apellidoLabel, dniLabel, telefonoLabel;
    private JTextField nombreTextField, apellidoTextField, dniTextField, telefonoTextField;
    private JButton registrarButton;

    private PersonaDAO personaDAO;
    private ClienteDAO clienteDAO;

    public RegistrarClienteFrame() {
        // Configurar ventana
        this.setTitle("Registrar Cliente");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        // Crear componentes
        nombreLabel = new JLabel("Nombre:");
        apellidoLabel = new JLabel("Apellido:");
        dniLabel = new JLabel("DNI:");

        nombreTextField = new JTextField(10);
        apellidoTextField = new JTextField(10);
        dniTextField = new JTextField(10);

        registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });

        // Añadir componentes al contenedor
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(nombreLabel);
        panel.add(nombreTextField);
        panel.add(apellidoLabel);
        panel.add(apellidoTextField);
        panel.add(dniLabel);
        panel.add(dniTextField);
        panel.add(new JLabel());
        panel.add(registrarButton);

        this.add(panel);
    }

    private void registrarCliente() {
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String dni = dniTextField.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        personaDAO = new PersonaDAO();
        clienteDAO = new ClienteDAO();

        Persona persona = personaDAO.buscarPorDni(dni);

        if (persona != null) {
            JOptionPane.showMessageDialog(this, "Ya existe una persona registrada con el mismo DNI", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(nombre);
        nuevaPersona.setApellido(apellido);
        nuevaPersona.setDni(dni);


        List<Compra> compras = new ArrayList<>();
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setHistorialCompras(compras);
        clienteDAO.guardar(cliente);
        JOptionPane.showMessageDialog(this, "Se registró el cliente con exito");
    }


}


