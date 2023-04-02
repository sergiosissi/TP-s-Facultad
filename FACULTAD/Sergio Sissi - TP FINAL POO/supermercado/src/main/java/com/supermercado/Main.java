package com.supermercado;

import com.supermercado.frame.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new SupermercadoFrame());
        //SwingUtilities.invokeLater(() -> new SupermercadoFrame());
    }
}
