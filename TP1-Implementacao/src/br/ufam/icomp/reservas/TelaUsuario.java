package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaUsuario extends JFrame {
    private JTextField tfNome;
    private JComboBox<String> cbTipo;

    public TelaUsuario() {
        super("Cadastro de Usuários");

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel lbNome = new JLabel("Nome:");
        tfNome = new JTextField(20);

        JLabel lbTipo = new JLabel("Tipo:");
        cbTipo = new JComboBox<>(new String[]{"Professor", "Funcionário", "Aluno"});

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarUsuario());

        JButton btnBuscar = new JButton("Buscar todos");
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
            JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
            tfNome.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar usuário.");
        }
    }

    private void abrirListaDeUsuarios() {
        JFrame listaFrame = new JFrame("Usuários Cadastrados");
        listaFrame.setSize(500, 400);
        listaFrame.setLayout(new BorderLayout());

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
                    int confirm = JOptionPane.showConfirmDialog(listaFrame,
                            "Tem certeza que deseja deletar este usuário?", "Confirmar exclusão",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try (Connection delConn = Banco.conectar();
                             PreparedStatement delStmt = delConn.prepareStatement("DELETE FROM Usuario WHERE id = ?")) {
                            delStmt.setInt(1, id);
                            delStmt.executeUpdate();
                            JOptionPane.showMessageDialog(listaFrame, "Usuário deletado com sucesso!");
                            listaFrame.dispose();
                            abrirListaDeUsuarios();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(listaFrame, "Erro ao deletar usuário.");
                        }
                    }
                });
                linha.add(deletarBtn);

                panel.add(linha);
            }

            if (!temUsuario) {
                JOptionPane.showMessageDialog(this, "Não há usuários cadastrados.");
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar usuários.");
            return;
        }

        JScrollPane scroll = new JScrollPane(panel);
        listaFrame.add(scroll, BorderLayout.CENTER);
        listaFrame.setVisible(true);
    }

    private void abrirEdicaoUsuario(int id, String nomeAtual, String tipoAtual) {
        JFrame edicaoFrame = new JFrame("Editar Usuário");
        edicaoFrame.setSize(300, 200);
        edicaoFrame.setLayout(new FlowLayout());

        JTextField tfNovoNome = new JTextField(nomeAtual, 20);
        JComboBox<String> cbNovoTipo = new JComboBox<>(new String[]{"Professor", "Funcionário", "Aluno"});
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
                JOptionPane.showMessageDialog(edicaoFrame, "Usuário atualizado com sucesso!");
                edicaoFrame.dispose();
                abrirListaDeUsuarios();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(edicaoFrame, "Erro ao atualizar usuário.");
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
