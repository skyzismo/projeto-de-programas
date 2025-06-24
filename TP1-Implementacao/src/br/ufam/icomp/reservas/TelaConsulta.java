package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaConsulta extends JFrame {
    private JTextArea txtResultado = new JTextArea();

    public TelaConsulta() {
        setTitle("Consulta de Reservas");
        setSize(500, 300);
        setLayout(new BorderLayout());
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        consultar();
    }

    private void consultar() {
        try (Connection conn = Banco.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT R.data, R.hora, S.nome AS sala, U.nome AS usuario FROM Reserva R JOIN Sala S ON R.sala_id = S.id JOIN Usuario U ON R.usuario_id = U.id")) {
            while (rs.next()) {
                txtResultado.append(rs.getString("data") + " " + rs.getString("hora") + " - Sala: " + rs.getString("sala") + ", Usuário: " + rs.getString("usuario") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtResultado.setText("Erro ao consultar reservas.");
        }
    }
}
