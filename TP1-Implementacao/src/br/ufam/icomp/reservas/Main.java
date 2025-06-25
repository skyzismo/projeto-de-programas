/**
 * Classe principal que inicia a aplicação.
 * 
 * @author Lucas Gadelha - 22050517
 * @version 1.0
 * @since 2025-06-23
 */

package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;

public class Main {
    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizados)
     */
    
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