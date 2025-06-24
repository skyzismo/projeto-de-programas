package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaRelatorio extends JFrame {
    private JTextArea txtRelatorio = new JTextArea();

    public TelaRelatorio() {
        setTitle("Relatorio de Reservas");
        setSize(600, 400);  // Aumentei o tamanho para melhor visualização
        setLayout(new BorderLayout());
        txtRelatorio.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fonte monoespaçada para alinhamento
        add(new JScrollPane(txtRelatorio), BorderLayout.CENTER);
        gerar();
    }

    private void gerar() {
        txtRelatorio.setText(""); // Limpa o conteúdo anterior
        
        try (Connection conn = Banco.conectar(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(
                 "SELECT S.nome AS sala, R.data, R.hora_inicio, R.hora_fim, U.nome AS usuario, U.tipo " +
                 "FROM Reserva R " +
                 "JOIN Sala S ON R.sala_id = S.id " +
                 "JOIN Usuario U ON R.usuario_id = U.id " +
                 "ORDER BY R.data, R.hora_inicio, S.nome")) {
            
            String salaAtual = "";
            String dataAtual = "";
            
            while (rs.next()) {
                String sala = rs.getString("sala");
                String data = rs.getString("data");
                String horaInicio = rs.getString("hora_inicio");
                String horaFim = rs.getString("hora_fim");
                String usuario = rs.getString("usuario");
                String tipoUsuario = rs.getString("tipo");
                
                // Agrupa por sala
                if (!sala.equals(salaAtual)) {
                    salaAtual = sala;
                    txtRelatorio.append("\nSALA: " + sala + "\n");
                    dataAtual = ""; // Reseta a data quando muda de sala
                }
                
                // Agrupa por data
                if (!data.equals(dataAtual)) {
                    dataAtual = data;
                    txtRelatorio.append("\n  DATA: " + data + "\n");
                }
                
                // Adiciona os horários e usuário
                txtRelatorio.append(String.format(
                    "    HORARIO: %s as %s - %s (%s)\n", 
                    horaInicio, 
                    horaFim, 
                    usuario, 
                    tipoUsuario));
            }
            
            if (salaAtual.isEmpty()) {
                txtRelatorio.setText("Nao ha reservas cadastradas.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            txtRelatorio.setText("Erro ao gerar relatorio.");
        }
    }
}