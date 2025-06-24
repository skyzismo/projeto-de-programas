package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Configura o Look and Feel antes de criar a interface
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // Configurações padrão
            UIManager.put("Button.background", new Color(163, 178, 184));
            // UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 12));
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 12));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new InterfacePrincipal().setVisible(true));
    }
}