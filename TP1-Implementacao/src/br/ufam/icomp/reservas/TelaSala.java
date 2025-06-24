package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaSala extends JFrame {
    private JTextField tfNome;
    private JComboBox<String> cbTipo;
    private JButton btnSalvar, btnBuscar, btnRemover, btnAtualizar;

    public TelaSala() {
        super("Gerenciar Salas");
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Nome:"));
        tfNome = new JTextField();
        add(tfNome);

        add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{
            "Sala de Reuniões", 
            "Sala de Seminários", 
            "Laboratório de Graduação"
        });
        add(cbTipo);

        btnSalvar = new JButton("Salvar");
        btnBuscar = new JButton("Buscar Todas");
        btnRemover = new JButton("Remover");
        btnAtualizar = new JButton("Atualizar");

        add(btnSalvar);
        add(btnBuscar);
        add(btnRemover);
        add(btnAtualizar);

        btnSalvar.addActionListener(e -> salvarSala());
        btnBuscar.addActionListener(e -> buscarSalas());
        btnRemover.addActionListener(e -> removerSala());
        btnAtualizar.addActionListener(e -> atualizarSala());

        setSize(500, 300);
        setVisible(true);
    }

    private void salvarSala() {
        try (Connection conn = Banco.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO sala (nome, tipo) VALUES (?, ?)");
            stmt.setString(1, tfNome.getText());
            stmt.setString(2, cbTipo.getSelectedItem().toString());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sala salva com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar sala.");
        }
    }

    private void buscarSalas() {
        try (Connection conn = Banco.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sala");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(" - Nome: ").append(rs.getString("nome"))
                  .append(" - Tipo: ").append(rs.getString("tipo"))
                  .append("\n");
            }

            if (sb.length() == 0) {
                JOptionPane.showMessageDialog(this, "Não há salas cadastradas.");
            } else {
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 200));
                JOptionPane.showMessageDialog(this, scrollPane, "Lista de Salas", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar salas.");
        }
    }

    private void removerSala() {
        String nomeRemover = tfNome.getText();
        try (Connection conn = Banco.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM sala WHERE nome = ?");
            stmt.setString(1, nomeRemover);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Sala removida com sucesso.");
                tfNome.setText("");
                cbTipo.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Sala não encontrada.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void atualizarSala() {
        String nome = tfNome.getText();
        String tipo = cbTipo.getSelectedItem().toString();
        try (Connection conn = Banco.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE sala SET tipo = ? WHERE nome = ?");
            stmt.setString(1, tipo);
            stmt.setString(2, nome);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Sala atualizada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Sala não encontrada para atualizar.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
