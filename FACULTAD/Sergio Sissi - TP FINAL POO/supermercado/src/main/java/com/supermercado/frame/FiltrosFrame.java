package com.supermercado.frame;

import com.supermercado.filtro.FiltroDepartamento;
import com.supermercado.filtro.FiltroNombre;
import com.supermercado.filtro.FiltroPrecioMayor;
import com.supermercado.filtro.FiltroPrecioMenor;
import com.supermercado.frame.RealizarCompraFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiltrosFrame extends JFrame {
    private RealizarCompraFrame realizarCompraFrame;
    private JTextField departamentoTextField;
    private JTextField precioMenorTextField;
    private JTextField precioMayorTextField;
    private JTextField nombreTextField;

    public FiltrosFrame(RealizarCompraFrame realizarCompraFrame) {
        this.realizarCompraFrame = realizarCompraFrame;

        // Configurar la ventana de FiltrosFrame
        setTitle("Filtros");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear y configurar los componentes de la interfaz de usuario
        departamentoTextField = new JTextField();
        departamentoTextField.setColumns(20);
        precioMenorTextField = new JTextField();
        precioMenorTextField.setColumns(20);
        precioMayorTextField = new JTextField();
        precioMayorTextField.setColumns(20);
        nombreTextField = new JTextField();
        nombreTextField.setColumns(20);
        String[] operacionesLogicas = {"","AND", "OR"}; // Opciones predefinidas para las operaciones lógicas
        JComboBox<String> operacionesLogicasComboBox = new JComboBox<>(operacionesLogicas);
        JButton aplicarButton = new JButton("Aplicar");
        aplicarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los filtros desde la interfaz de usuario
                String filtroDepartamentoTexto = departamentoTextField.getText();
                String filtroPrecioMenorTexto = precioMenorTextField.getText();
                String filtroPrecioMayorTexto = precioMayorTextField.getText();
                String filtroNombreTexto = nombreTextField.getText();
                String operacionLogicaTexto = (String) operacionesLogicasComboBox.getSelectedItem();

                // Establecer los valores de los filtros en la ventana de RealizarCompraFrame
                realizarCompraFrame.setFiltroDepartamento(filtroDepartamentoTexto);
                realizarCompraFrame.setFiltroPrecioMenor(filtroPrecioMenorTexto);
                realizarCompraFrame.setFiltroPrecioMayor(filtroPrecioMayorTexto);
                realizarCompraFrame.setFiltroNombre(filtroNombreTexto);

                if(operacionLogicaTexto == null || operacionLogicaTexto.equals("")){
                    if(filtroDepartamentoTexto != null && !filtroDepartamentoTexto.equals("")){
                        realizarCompraFrame.filtrarPorDepartamento(filtroDepartamentoTexto);
                    }else{
                        if(filtroPrecioMenorTexto != null && !filtroPrecioMenorTexto.equals("")){
                            realizarCompraFrame.filtrarPorPrecioMenor(Double.valueOf(filtroPrecioMenorTexto));
                        }else{
                            if(filtroPrecioMayorTexto != null && !filtroPrecioMayorTexto.equals("")){
                                realizarCompraFrame.filtrarPorPrecioMayor(Double.valueOf(filtroPrecioMayorTexto));
                            }else{
                                if(filtroNombreTexto != null && !filtroNombreTexto.equals("")){
                                    realizarCompraFrame.filtrarPorNombre(filtroNombreTexto);
                                }
                            }

                        }

                    }
                }else{
                    if ((filtroDepartamentoTexto != null && !filtroDepartamentoTexto.equals(""))
                            && (filtroPrecioMenorTexto != null && !filtroPrecioMenorTexto.equals(""))) {

                        FiltroDepartamento filtroDepartamento = new FiltroDepartamento(filtroDepartamentoTexto);
                        FiltroPrecioMenor filtroPrecioMenor = new FiltroPrecioMenor(Double.valueOf(filtroPrecioMenorTexto));

                        if(operacionLogicaTexto.equals("AND")){
                            realizarCompraFrame.filtrarAND(filtroDepartamento, filtroPrecioMenor);
                        }else {
                            realizarCompraFrame.filtrarOR(filtroDepartamento, filtroPrecioMenor);
                        }
                    } else if ((filtroDepartamentoTexto != null && !filtroDepartamentoTexto.equals(""))
                            && (filtroPrecioMayorTexto != null && !filtroPrecioMayorTexto.equals(""))) {

                        FiltroDepartamento filtroDepartamento = new FiltroDepartamento(filtroDepartamentoTexto);
                        FiltroPrecioMayor filtroPrecioMayor = new FiltroPrecioMayor(Double.valueOf(filtroPrecioMayorTexto));

                        if(operacionLogicaTexto.equals("AND")){
                            realizarCompraFrame.filtrarAND(filtroDepartamento, filtroPrecioMayor);
                        }else {
                            realizarCompraFrame.filtrarOR(filtroDepartamento, filtroPrecioMayor);
                        }

                    } else if ((filtroDepartamentoTexto != null && !filtroDepartamentoTexto.equals(""))
                            && (filtroNombreTexto != null && !filtroNombreTexto.equals(""))) {

                        FiltroDepartamento filtroDepartamento = new FiltroDepartamento(filtroDepartamentoTexto);
                        FiltroNombre filtroNombre = new FiltroNombre((filtroNombreTexto));

                        if(operacionLogicaTexto.equals("AND")){
                            realizarCompraFrame.filtrarAND(filtroDepartamento, filtroNombre);
                        }else {
                            realizarCompraFrame.filtrarOR(filtroDepartamento, filtroNombre);
                        }

                    } else if ((filtroPrecioMenorTexto != null && !filtroPrecioMenorTexto.equals(""))
                            && (filtroPrecioMayorTexto != null && !filtroPrecioMayorTexto.equals(""))) {

                        FiltroPrecioMenor filtroPrecioMenor = new FiltroPrecioMenor(Double.valueOf(filtroPrecioMenorTexto));
                        FiltroPrecioMayor filtroPrecioMayor = new FiltroPrecioMayor(Double.valueOf(filtroPrecioMayorTexto));

                        if(operacionLogicaTexto.equals("AND")){
                            realizarCompraFrame.filtrarAND(filtroPrecioMenor, filtroPrecioMayor);
                        }else {
                            realizarCompraFrame.filtrarOR(filtroPrecioMenor, filtroPrecioMayor);
                        }

                    } else if ((filtroPrecioMenorTexto != null && !filtroPrecioMenorTexto.equals(""))
                            && (filtroNombreTexto != null && !filtroNombreTexto.equals(""))) {

                        FiltroPrecioMenor filtroPrecioMenor = new FiltroPrecioMenor(Double.valueOf(filtroPrecioMenorTexto));
                        FiltroNombre filtroNombre = new FiltroNombre((filtroNombreTexto));
                        if(operacionLogicaTexto.equals("AND")){
                            realizarCompraFrame.filtrarAND(filtroPrecioMenor, filtroNombre);
                        }else {
                            realizarCompraFrame.filtrarOR(filtroPrecioMenor, filtroNombre);
                        }

                    } else if ((filtroPrecioMayorTexto != null && !filtroPrecioMayorTexto.equals(""))
                            && (filtroNombreTexto != null && !filtroNombreTexto.equals(""))) {

                        FiltroPrecioMayor filtroPrecioMayor = new FiltroPrecioMayor(Double.valueOf(filtroPrecioMayorTexto));
                        FiltroNombre filtroNombre = new FiltroNombre((filtroNombreTexto));
                        if(operacionLogicaTexto.equals("AND")){
                            realizarCompraFrame.filtrarAND(filtroPrecioMayor, filtroNombre);
                        }else {
                            realizarCompraFrame.filtrarOR(filtroPrecioMayor, filtroNombre);
                        }

                    } else {
                        // Si no se cumple ninguna de las combinaciones anteriores, se ejecuta este bloque
                        // Aquí podrías establecer un comportamiento predeterminado o mostrar un mensaje de error, por ejemplo
                        System.out.println("No se ha seleccionado una combinación válida.");
                    }
                }


                // Cerrar la ventana de FiltrosFrame
                dispose();
            }
        });

        // Configurar el diseño de la ventana de FiltrosFrame
        setLayout(new FlowLayout());
        add(new JLabel("Filtro Departamento:"));
        add(departamentoTextField);
        add(new JLabel("Filtro Precio Menor:"));
        add(precioMenorTextField);
        add(new JLabel("Filtro Precio Mayor:"));
        add(precioMayorTextField);
        add(new JLabel("Filtro Nombre:"));
        add(nombreTextField);
        operacionesLogicasComboBox.setSelectedIndex(0);
        operacionesLogicasComboBox.setBounds(0, nombreTextField.getY() + nombreTextField.getHeight() + 10, 200, 30);
        add(operacionesLogicasComboBox);
        add(aplicarButton);

        // Mostrar la ventana de FiltrosFrame
        setVisible(true);
    }
}
