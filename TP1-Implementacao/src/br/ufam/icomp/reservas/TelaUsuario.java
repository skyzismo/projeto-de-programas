/**
 * Classe responsável pela interface de cadastro e gerenciamento de usuários.
 * 
 * @author Lucas Gadelha - 22050517
 * @version 1.0
 * @since 2025-06-23
 */

package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaUsuario extends JFrame {
    private JTextField tfNome;
    private JComboBox<String> cbTipo;
    private JFrame listaUsuariosFrame;

    public TelaUsuario() {
        super("Cadastro de Usuarios");

        setSize(600, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        JLabel lbNome = new JLabel("Nome:");
        tfNome = new JTextField(20);

        JLabel lbTipo = new JLabel("Tipo:");
        cbTipo = new JComboBox<>(new String[]{"Professor", "Funcionario", "Aluno"});

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarUsuario());

        JButton btnBuscar = new JButton("Consultar Usuarios");
        btnBuscar.addActionListener(e -> abrirListaDeUsuarios());

        add(lbNome);
        add(tfNome);
        add(lbTipo);
        add(cbTipo);
        add(btnSalvar);
        add(btnBuscar);

        setVisible(true);
    }

    private void salvarUsuario() {
        String nome = tfNome.getText();
        String tipo = cbTipo.getSelectedItem().toString();

        try (Connection conn = Banco.conectar();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Usuario (nome, tipo) VALUES (?, ?)")) {
            stmt.setString(1, nome);
            stmt.setString(2, tipo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuario salvo com sucesso!");
            tfNome.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar usuario.");
        }
    }

    private void abrirListaDeUsuarios() {
        if (listaUsuariosFrame != null) {
            listaUsuariosFrame.dispose();
        }

        listaUsuariosFrame = new JFrame("Usuarios Cadastrados");
        listaUsuariosFrame.setSize(500, 400);
        listaUsuariosFrame.setLocationRelativeTo(null);
        listaUsuariosFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        try (Connection conn = Banco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario")) {

            boolean temUsuario = false;
            while (rs.next()) {
                temUsuario = true;
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");

                JPanel linha = new JPanel(new FlowLayout(FlowLayout.LEFT));
                linha.add(new JLabel("ID: " + id + " | Nome: " + nome + " | Tipo: " + tipo));

                JButton editarBtn = new JButton("Editar");
                editarBtn.addActionListener(ev -> abrirEdicaoUsuario(id, nome, tipo));
                linha.add(editarBtn);

                JButton deletarBtn = new JButton("Deletar");
                deletarBtn.addActionListener(ev -> {
                    int confirm = JOptionPane.showConfirmDialog(listaUsuariosFrame,
                            "Tem certeza que deseja deletar este usuario?", "Confirmar exclusao",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try (Connection delConn = Banco.conectar();
                             PreparedStatement delStmt = delConn.prepareStatement("DELETE FROM Usuario WHERE id = ?")) {
                            delStmt.setInt(1, id);
                            delStmt.executeUpdate();
                            JOptionPane.showMessageDialog(listaUsuariosFrame, "Usuario deletado com sucesso!");
                            listaUsuariosFrame.dispose();
                            abrirListaDeUsuarios();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(listaUsuariosFrame, "Erro ao deletar usuario.");
                        }
                    }
                });
                linha.add(deletarBtn);

                panel.add(linha);
            }

            if (!temUsuario) {
                JOptionPane.showMessageDialog(this, "Nao ha usuarios cadastrados.");
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar usuarios.");
            return;
        }

        JScrollPane scroll = new JScrollPane(panel);
        listaUsuariosFrame.add(scroll, BorderLayout.CENTER);
        listaUsuariosFrame.setVisible(true);
    }

    /**
     * Abre a interface de edição de usuário.
     *
     * @param id ID do usuário
     * @param nomeAtual Nome atual do usuário
     * @param tipoAtual Tipo atual do usuário
     */

    private void abrirEdicaoUsuario(int id, String nomeAtual, String tipoAtual) {
        JFrame edicaoFrame = new JFrame("Editar Usuario");
        edicaoFrame.setSize(350, 200);
        edicaoFrame.setLayout(new FlowLayout());

        JTextField tfNovoNome = new JTextField(nomeAtual, 20);
        JComboBox<String> cbNovoTipo = new JComboBox<>(new String[]{"Professor", "Funcionario", "Aluno"});
        cbNovoTipo.setSelectedItem(tipoAtual);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(ev -> {
            String novoNome = tfNovoNome.getText();
            String novoTipo = cbNovoTipo.getSelectedItem().toString();

            try (Connection conn = Banco.conectar();
                 PreparedStatement stmt = conn.prepareStatement("UPDATE Usuario SET nome = ?, tipo = ? WHERE id = ?")) {
                stmt.setString(1, novoNome);
                stmt.setString(2, novoTipo);
                stmt.setInt(3, id);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(edicaoFrame, "Usuario atualizado com sucesso!");
                edicaoFrame.dispose();
                listaUsuariosFrame.dispose();
                abrirListaDeUsuarios();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(edicaoFrame, "Erro ao atualizar usuario.");
            }
        });

        edicaoFrame.add(new JLabel("Nome:"));
        edicaoFrame.add(tfNovoNome);
        edicaoFrame.add(new JLabel("Tipo:"));
        edicaoFrame.add(cbNovoTipo);
        edicaoFrame.add(btnAtualizar);

        edicaoFrame.setVisible(true);
    }
}