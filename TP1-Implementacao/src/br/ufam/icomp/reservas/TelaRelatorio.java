package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaRelatorio extends JFrame {
    private JTextArea txtRelatorio = new JTextArea();

    public TelaRelatorio() {
        setTitle("Relatório de Reservas");
        setSize(500, 300);
        setLayout(new BorderLayout());
        add(new JScrollPane(txtRelatorio), BorderLayout.CENTER);
        gerar();
    }

    private void gerar() {
        try (Connection conn = Banco.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT S.nome AS sala, R.data, R.hora, U.nome AS usuario FROM Reserva R JOIN Sala S ON R.sala_id = S.id JOIN Usuario U ON R.usuario_id = U.id ORDER BY S.nome, R.data, R.hora")) {
            while (rs.next()) {
                txtRelatorio.append("Sala: " + rs.getString("sala") + "\n  " + rs.getString("data") + " às " + rs.getString("hora") + " - " + rs.getString("usuario") + "\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtRelatorio.setText("Erro ao gerar relatório.");
        }
    }
}
