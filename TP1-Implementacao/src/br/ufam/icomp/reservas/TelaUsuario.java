package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaUsuario extends JFrame {
    private JTextField tfNome;
    private JComboBox<String> cbTipo;
    private JButton btnSalvar, btnBuscar, btnRemover, btnAtualizar, btnBuscarTodas;

    public TelaUsuario() {
        setTitle("Gerenciar Usuários");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        tfNome = new JTextField();
        cbTipo = new JComboBox<>(new String[]{"Professor", "Funcionário", "Aluno"});

        btnSalvar = new JButton("Salvar");
        btnBuscar = new JButton("Buscar");
        btnRemover = new JButton("Remover");
        btnAtualizar = new JButton("Atualizar");
        btnBuscarTodas = new JButton("Buscar Todos");

        add(new JLabel("Nome:"));
        add(tfNome);
        add(new JLabel("Tipo:"));
        add(cbTipo);

        add(btnSalvar);
        add(btnBuscar);
        add(btnRemover);
        add(btnAtualizar);
        add(btnBuscarTodas);

        btnSalvar.addActionListener(e -> salvarUsuario());
        btnBuscar.addActionListener(e -> buscarUsuario());
        btnRemover.addActionListener(e -> removerUsuario());
        btnAtualizar.addActionListener(e -> atualizarUsuario());
        btnBuscarTodas.addActionListener(e -> buscarTodosUsuarios());

        setVisible(true);
    }

    private void salvarUsuario() {
        try (Connection conn = Banco.conectar()) {
            String sql = "INSERT INTO Usuario (nome, cargo) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfNome.getText());
            stmt.setString(2, cbTipo.getSelectedItem().toString());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar usuário.");
        }
    }

    private void buscarUsuario() {
        String nome = tfNome.getText();
        try (Connection conn = Banco.conectar()) {
            String sql = "SELECT * FROM Usuario WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tfNome.setText(rs.getString("nome"));
                cbTipo.setSelectedItem(rs.getString("cargo"));
                JOptionPane.showMessageDialog(this, "Usuário encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar usuário.");
        }
    }

    private void removerUsuario() {
        String nome = tfNome.getText();
        try (Connection conn = Banco.conectar()) {
            String sql = "DELETE FROM Usuario WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            int afetadas = stmt.executeUpdate();
            if (afetadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuário removido.");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao remover usuário.");
        }
    }

    private void atualizarUsuario() {
        String nome = tfNome.getText();
        try (Connection conn = Banco.conectar()) {
            String sql = "UPDATE Usuario SET cargo = ? WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cbTipo.getSelectedItem().toString());
            stmt.setString(2, nome);
            int afetadas = stmt.executeUpdate();
            if (afetadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuário atualizado.");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar usuário.");
        }
    }

    private void buscarTodosUsuarios() {
        try (Connection conn = Banco.conectar()) {
            String sql = "SELECT * FROM Usuario";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            StringBuilder resultado = new StringBuilder();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id"))
                        .append(" - Nome: ").append(rs.getString("nome"))
                        .append(" - cargo: ").append(rs.getString("cargo"))
                        .append("\n");
            }

            if (resultado.length() == 0) {
                JOptionPane.showMessageDialog(this, "Não há usuários cadastrados.");
            } else {
                JTextArea textArea = new JTextArea(resultado.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(380, 200));
                JOptionPane.showMessageDialog(this, scrollPane, "Lista de Usuários", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar usuários.");
        }
    }
} 
