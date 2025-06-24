package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class TelaReserva extends JFrame {
    private JComboBox<String> cbSalas = new JComboBox<>();
    private JComboBox<String> cbUsuarios = new JComboBox<>();
    private JTextField txtData = new JTextField(10);
    private JTextField txtHora = new JTextField(5);

    public TelaReserva() {
        setTitle("Reservar Sala");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        carregarSalas();
        carregarUsuarios();

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(e -> reservarSala());

        add(new JLabel("Sala:"));
        add(cbSalas);
        add(new JLabel("Usuário:"));
        add(cbUsuarios);
        add(new JLabel("Data (AAAA-MM-DD):"));
        add(txtData);
        add(new JLabel("Hora (HH:MM):"));
        add(txtHora);
        add(btnReservar);
    }

    private void carregarSalas() {
        try (Connection conn = Banco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, nome FROM Sala")) {
            Vector<String> salas = new Vector<>();
            while (rs.next()) {
                salas.add(rs.getInt("id") + ": " + rs.getString("nome"));
            }
            cbSalas.setModel(new DefaultComboBoxModel<>(salas));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarUsuarios() {
        try (Connection conn = Banco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, nome FROM Usuario")) {
            Vector<String> usuarios = new Vector<>();
            while (rs.next()) {
                usuarios.add(rs.getInt("id") + ": " + rs.getString("nome"));
            }
            cbUsuarios.setModel(new DefaultComboBoxModel<>(usuarios));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void reservarSala() {
        try (Connection conn = Banco.conectar();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Reserva (sala_id, usuario_id, data, hora) VALUES (?, ?, ?, ?)")) {

            String salaStr = cbSalas.getSelectedItem().toString();
            String usuarioStr = cbUsuarios.getSelectedItem().toString();

            stmt.setInt(1, Integer.parseInt(salaStr.split(":" )[0]));
            stmt.setInt(2, Integer.parseInt(usuarioStr.split(":" )[0]));
            stmt.setString(3, txtData.getText());
            stmt.setString(4, txtHora.getText());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao realizar reserva.");
        }
    }
}
