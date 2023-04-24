package com.supermercado.frame;

import com.supermercado.filtro.*;
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
        setSize(600, 400);
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


                if(operacionLogicaTexto == null || operacionLogicaTexto.equals("")){
                    if(filtroDepartamentoTexto != null && !filtroDepartamentoTexto.equals("")){
                        FiltroDepartamento filtroDepartamento = new FiltroDepartamento(filtroDepartamentoTexto);
                        realizarCompraFrame.aplicarFiltro(filtroDepartamento);
                    }else{
                        if(filtroPrecioMenorTexto != null && !filtroPrecioMenorTexto.equals("")){
                            FiltroPrecioMenor filtroPrecioMenor = new FiltroPrecioMenor(Double.valueOf(filtroPrecioMenorTexto));
                            realizarCompraFrame.aplicarFiltro(filtroPrecioMenor);
                        }else{
                            if(filtroPrecioMayorTexto != null && !filtroPrecioMayorTexto.equals("")){
                                FiltroPrecioMayor filtroPrecioMayor = new FiltroPrecioMayor(Double.valueOf(filtroPrecioMenorTexto));
                                realizarCompraFrame.aplicarFiltro(filtroPrecioMayor);
                            }else{
                                if(filtroNombreTexto != null && !filtroNombreTexto.equals("")){
                                    FiltroNombre filtroNombre = new FiltroNombre(filtroNombreTexto);
                                    realizarCompraFrame.aplicarFiltro(filtroNombre);
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
                            FiltroAnd filtroAnd = new FiltroAnd(filtroDepartamento, filtroPrecioMenor);
                            realizarCompraFrame.aplicarFiltro(filtroAnd);
                        }else {
                            FiltroOr filtroOr = new FiltroOr(filtroDepartamento, filtroPrecioMenor);
                            realizarCompraFrame.aplicarFiltro(filtroOr);
                        }
                    } else if ((filtroDepartamentoTexto != null && !filtroDepartamentoTexto.equals(""))
                            && (filtroPrecioMayorTexto != null && !filtroPrecioMayorTexto.equals(""))) {

                        FiltroDepartamento filtroDepartamento = new FiltroDepartamento(filtroDepartamentoTexto);
                        FiltroPrecioMayor filtroPrecioMayor = new FiltroPrecioMayor(Double.valueOf(filtroPrecioMayorTexto));

                        if(operacionLogicaTexto.equals("AND")){
                            FiltroAnd filtroAnd = new FiltroAnd(filtroDepartamento, filtroPrecioMayor);
                            realizarCompraFrame.aplicarFiltro(filtroAnd);
                        }else {
                            FiltroOr filtroOr = new FiltroOr(filtroDepartamento, filtroPrecioMayor);
                            realizarCompraFrame.aplicarFiltro(filtroOr);
                        }

                    } else if ((filtroDepartamentoTexto != null && !filtroDepartamentoTexto.equals(""))
                            && (filtroNombreTexto != null && !filtroNombreTexto.equals(""))) {

                        FiltroDepartamento filtroDepartamento = new FiltroDepartamento(filtroDepartamentoTexto);
                        FiltroNombre filtroNombre = new FiltroNombre((filtroNombreTexto));

                        if(operacionLogicaTexto.equals("AND")){
                            FiltroAnd filtroAnd = new FiltroAnd(filtroDepartamento, filtroNombre);
                            realizarCompraFrame.aplicarFiltro(filtroAnd);
                        }else {
                            FiltroOr filtroOr = new FiltroOr(filtroDepartamento, filtroNombre);
                            realizarCompraFrame.aplicarFiltro(filtroOr);
                        }

                    } else if ((filtroPrecioMenorTexto != null && !filtroPrecioMenorTexto.equals(""))
                            && (filtroPrecioMayorTexto != null && !filtroPrecioMayorTexto.equals(""))) {

                        FiltroPrecioMenor filtroPrecioMenor = new FiltroPrecioMenor(Double.valueOf(filtroPrecioMenorTexto));
                        FiltroPrecioMayor filtroPrecioMayor = new FiltroPrecioMayor(Double.valueOf(filtroPrecioMayorTexto));

                        if(operacionLogicaTexto.equals("AND")){
                            FiltroAnd filtroAnd = new FiltroAnd(filtroPrecioMayor, filtroPrecioMenor);
                            realizarCompraFrame.aplicarFiltro(filtroAnd);
                        }else {
                            FiltroOr filtroOr = new FiltroOr(filtroPrecioMayor, filtroPrecioMenor);
                            realizarCompraFrame.aplicarFiltro(filtroOr);
                        }

                    } else if ((filtroPrecioMenorTexto != null && !filtroPrecioMenorTexto.equals(""))
                            && (filtroNombreTexto != null && !filtroNombreTexto.equals(""))) {

                        FiltroPrecioMenor filtroPrecioMenor = new FiltroPrecioMenor(Double.valueOf(filtroPrecioMenorTexto));
                        FiltroNombre filtroNombre = new FiltroNombre((filtroNombreTexto));
                        if(operacionLogicaTexto.equals("AND")){
                            FiltroAnd filtroAnd = new FiltroAnd(filtroNombre, filtroPrecioMenor);
                            realizarCompraFrame.aplicarFiltro(filtroAnd);
                        }else {
                            FiltroOr filtroOr = new FiltroOr(filtroNombre, filtroPrecioMenor);
                            realizarCompraFrame.aplicarFiltro(filtroOr);
                        }

                    } else if ((filtroPrecioMayorTexto != null && !filtroPrecioMayorTexto.equals(""))
                            && (filtroNombreTexto != null && !filtroNombreTexto.equals(""))) {

                        FiltroPrecioMayor filtroPrecioMayor = new FiltroPrecioMayor(Double.valueOf(filtroPrecioMayorTexto));
                        FiltroNombre filtroNombre = new FiltroNombre((filtroNombreTexto));
                        if(operacionLogicaTexto.equals("AND")){
                            FiltroAnd filtroAnd = new FiltroAnd(filtroPrecioMayor, filtroNombre);
                            realizarCompraFrame.aplicarFiltro(filtroAnd);
                        }else {
                            FiltroOr filtroOr = new FiltroOr(filtroPrecioMayor, filtroNombre);
                            realizarCompraFrame.aplicarFiltro(filtroOr);
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
