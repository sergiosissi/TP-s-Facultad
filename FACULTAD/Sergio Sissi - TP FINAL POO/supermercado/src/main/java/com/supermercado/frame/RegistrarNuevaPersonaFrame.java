package com.supermercado.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.compra.Compra;
import com.supermercado.dao.ClienteDAO;
import com.supermercado.dao.EmpleadoDAO;
import com.supermercado.dao.PersonaDAO;
import com.supermercado.persona.Cliente;
import com.supermercado.persona.Empleado;
import com.supermercado.persona.Persona;

public class RegistrarNuevaPersonaFrame extends JFrame {
    private JLabel nombreLabel, apellidoLabel, dniLabel, telefonoLabel;
    private JTextField nombreTextField, apellidoTextField, dniTextField, telefonoTextField;
    private JButton registrarButton;
    private JCheckBox clienteCheckBox, empleadoCheckBox; // Agregamos los checkboxes

    private PersonaDAO personaDAO;
    private ClienteDAO clienteDAO;
    private EmpleadoDAO empleadoDAO;

    public RegistrarNuevaPersonaFrame() {
        // Configurar ventana
        this.setTitle("Registrar Persona");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        // Crear componentes
        nombreLabel = new JLabel("Nombre:");
        apellidoLabel = new JLabel("Apellido:");
        dniLabel = new JLabel("DNI:");
        clienteCheckBox = new JCheckBox("Cliente");
        empleadoCheckBox = new JCheckBox("Empleado");

        nombreTextField = new JTextField(10);
        apellidoTextField = new JTextField(10);
        dniTextField = new JTextField(10);

        registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPersona();
            }
        });

        // Añadir componentes al contenedor
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(nombreLabel);
        panel.add(nombreTextField);
        panel.add(apellidoLabel);
        panel.add(apellidoTextField);
        panel.add(dniLabel);
        panel.add(dniTextField);
        panel.add(clienteCheckBox);
        panel.add(empleadoCheckBox);
        panel.add(new JLabel());
        panel.add(registrarButton);

        this.add(panel);
    }

    private void registrarPersona() {
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String dni = dniTextField.getText();
        boolean esCliente = clienteCheckBox.isSelected();
        boolean esEmpleado = empleadoCheckBox.isSelected();

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        personaDAO = new PersonaDAO();

        Persona persona = personaDAO.buscarPorDni(dni);


        if (persona != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un cliente registrada con el mismo DNI", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(nombre);
        nuevaPersona.setApellido(apellido);
        nuevaPersona.setDni(dni);

        if (esCliente) {
            List<Compra> compras = new ArrayList<>();
            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDni(dni);
            cliente.setHistorialCompras(compras);
            clienteDAO = new ClienteDAO();
            clienteDAO.guardar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente registrado con éxito", "Cliente registrado", JOptionPane.INFORMATION_MESSAGE);
        }
        if (esEmpleado) {
            Empleado empleado = new Empleado();
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setDni(dni);
            empleado.setLegajo(dni);
            empleadoDAO = new EmpleadoDAO();
            empleadoDAO.guardar(empleado);
            JOptionPane.showMessageDialog(this, "Empleado registrado con éxito", "Empleado registrado", JOptionPane.INFORMATION_MESSAGE);
        }

       // personaDAO.guardar(nuevaPersona);
        JOptionPane.showMessageDialog(this, "Persona registrada con éxito", "Persona registrada", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void limpiarCampos() {
        nombreTextField.setText("");
        apellidoTextField.setText("");
        dniTextField.setText("");
        clienteCheckBox.setSelected(false);
        empleadoCheckBox.setSelected(false);
    }
}
