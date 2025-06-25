package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class InterfacePrincipal extends JFrame {
    // Cores personalizadas
    private static final Color COR_BOTAO = new Color(163, 178, 184);
    private static final Color COR_TEXTO = new Color(44, 59, 122);
    private static final Color COR_FUNDO = new Color(250, 250, 250);

    public InterfacePrincipal() {
        setTitle("EasyRoom: Sistema para Reserva de Salas do IComp");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(COR_FUNDO);
        
        // Painel da logo (esquerda)
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(COR_FUNDO);
        
        try {
            ImageIcon logoIcon = new ImageIcon("src/br/ufam/icomp/reservas/images/logo.png");
            JLabel logoLabel = new JLabel(logoIcon);
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoPanel.add(logoLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel logoLabel = new JLabel("EASY ROOM ICOMP");
            logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            logoLabel.setForeground(COR_TEXTO);
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoPanel.add(logoLabel, BorderLayout.CENTER);
        }
        
        // Painel dos botões (direita)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(COR_FUNDO);
        buttonPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        
        // Centraliza verticalmente
        buttonPanel.add(Box.createVerticalGlue());
        
        // Cria e adiciona os botões
        JButton btnSalas = criarBotaoMenu("Salas do Icomp");
        JButton btnUsuarios = criarBotaoMenu("Usuarios");
        JButton btnReservas = criarBotaoMenu("Reserva de Sala");
        JButton btnConsultar = criarBotaoMenu("Consulta de Reservas");
        JButton btnRelatorios = criarBotaoMenu("Gerar Relatorio");
        
        // Configura ações dos botões
        btnSalas.addActionListener(e -> new TelaSala().setVisible(true));
        btnUsuarios.addActionListener(e -> new TelaUsuario().setVisible(true));
        btnReservas.addActionListener(e -> new TelaReserva().setVisible(true));
        btnConsultar.addActionListener(e -> new TelaConsulta().setVisible(true));
        btnRelatorios.addActionListener(e -> new TelaRelatorio().setVisible(true));
        
        // Adiciona os botões com espaçamento
        adicionarBotaoComEspacamento(buttonPanel, btnSalas);
        adicionarBotaoComEspacamento(buttonPanel, btnUsuarios);
        adicionarBotaoComEspacamento(buttonPanel, btnReservas);
        adicionarBotaoComEspacamento(buttonPanel, btnConsultar);
        adicionarBotaoComEspacamento(buttonPanel, btnRelatorios);
        
        buttonPanel.add(Box.createVerticalGlue());
        
        // Adiciona os painéis ao layout principal
        mainPanel.add(logoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        
        setContentPane(mainPanel);
    }
    
    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(COR_BOTAO);
        btn.setForeground(COR_TEXTO);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(250, btn.getPreferredSize().height));
        
        // Remove qualquer efeito de hover/mouse
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        
        return btn;
    }
    
    private void adicionarBotaoComEspacamento(JPanel panel, JButton botao) {
        panel.add(botao);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }
}