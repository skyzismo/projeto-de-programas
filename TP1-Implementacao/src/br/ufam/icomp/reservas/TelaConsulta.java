package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaConsulta extends JFrame {
    private JTextArea txtResultado = new JTextArea();

    public TelaConsulta() {
        setTitle("Consulta de Reservas");
        setSize(600, 400);
        setLayout(new BorderLayout());
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        consultar();
    }

    private void consultar() {
        txtResultado.setText(""); // Limpa o conteúdo anterior
        
        try (Connection conn = Banco.conectar(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(
                 "SELECT R.data, R.hora_inicio, R.hora_fim, S.nome AS sala, U.nome AS usuario " +
                 "FROM Reserva R " +
                 "JOIN Sala S ON R.sala_id = S.id " +
                 "JOIN Usuario U ON R.usuario_id = U.id " +
                 "ORDER BY R.data, R.hora_inicio")) {
            
            while (rs.next()) {
                txtResultado.append(
                    rs.getString("data") + " - " +
                    rs.getString("hora_inicio") + " as " +
                    rs.getString("hora_fim") + "\n" +
                    "Sala: " + rs.getString("sala") + "\n" +
                    "Usuario: " + rs.getString("usuario") + "\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtResultado.setText("Erro ao consultar reservas.");
        }
    }
}