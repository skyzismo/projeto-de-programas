package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;

public class InterfacePrincipal extends JFrame {
    public InterfacePrincipal() {
        setTitle("EasyRoom: Sistema para Reserva de Salas do IComp");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JButton btnSalas = new JButton("Salas do Icomp");
        JButton btnUsuarios = new JButton("Usuarios");
        JButton btnReservas = new JButton("Reserva de Sala");
        JButton btnConsultar = new JButton("Consulta de Reserva de Sala");
        JButton btnRelatorios = new JButton("Gerar Relatorios");

        btnSalas.addActionListener(e -> new TelaSala().setVisible(true));
        btnUsuarios.addActionListener(e -> new TelaUsuario().setVisible(true));
        btnReservas.addActionListener(e -> new TelaReserva().setVisible(true));
        btnConsultar.addActionListener(e -> new TelaConsulta().setVisible(true));
        btnRelatorios.addActionListener(e -> new TelaRelatorio().setVisible(true));

        add(btnSalas);
        add(btnUsuarios);
        add(btnReservas);
        add(btnConsultar);
        add(btnRelatorios);
    }
}