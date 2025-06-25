package br.ufam.icomp.reservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaSala extends JFrame {
    private JTextField tfNome;
    private JComboBox<String> cbTipo;
    private JFrame listaSalasFrame;

    public TelaSala() {
        super("Cadastro de Salas do IComp");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);

        add(new JLabel("Nome:"));
        tfNome = new JTextField();
        add(tfNome);

        add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[] {
            "Sala de Reunioes", "Sala de Seminarios", "Laboratorio de Graduacao"
        });
        add(cbTipo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarSala());
        add(btnSalvar);

        JButton btnBuscar = new JButton("Consultar Salas");
        btnBuscar.addActionListener(e -> abrirListaDeSalas());
        add(btnBuscar);

        setVisible(true);
    }

    private void salvarSala() {
        String nome = tfNome.getText();
        String tipo = cbTipo.getSelectedItem().toString();

        try (Connection conn = Banco.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Sala (nome, tipo) VALUES (?, ?)")) {
            stmt.setString(1, nome);
            stmt.setString(2, tipo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sala cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar sala.");
        }
    }

    private void abrirListaDeSalas() {
        if (listaSalasFrame != null) {
            listaSalasFrame.dispose();
        }

        listaSalasFrame = new JFrame("Salas Cadastradas");
        listaSalasFrame.setSize(600, 400);
        listaSalasFrame.setLocationRelativeTo(null);
        listaSalasFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        try (Connection conn = Banco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Sala")) {

            boolean temSala = false;
            while (rs.next()) {
                temSala = true;
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");

                JPanel linha = new JPanel(new FlowLayout(FlowLayout.LEFT));
                linha.add(new JLabel("ID: " + id + " | Nome: " + nome + " | Tipo: " + tipo));

                JButton editarBtn = new JButton("Editar");
                editarBtn.addActionListener(ev -> abrirEdicaoSala(id, nome, tipo));
                linha.add(editarBtn);

                JButton deletarBtn = new JButton("Deletar");
                deletarBtn.addActionListener(ev -> {
                    int confirm = JOptionPane.showConfirmDialog(listaSalasFrame,
                            "Tem certeza que deseja deletar esta sala?", "Confirmar exclusão",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try (Connection delConn = Banco.conectar();
                             PreparedStatement delStmt = delConn.prepareStatement(
                                     "DELETE FROM Sala WHERE id = ?")) {
                            delStmt.setInt(1, id);
                            delStmt.executeUpdate();
                            JOptionPane.showMessageDialog(listaSalasFrame, "Sala deletada com sucesso!");
                            listaSalasFrame.dispose();
                            abrirListaDeSalas();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(listaSalasFrame, "Erro ao deletar sala.");
                        }
                    }
                });
                linha.add(deletarBtn);

                panel.add(linha);
            }

            if (!temSala) {
                JOptionPane.showMessageDialog(this, "Nao ha salas cadastradas.");
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar salas.");
            return;
        }

        JScrollPane scroll = new JScrollPane(panel);
        listaSalasFrame.add(scroll, BorderLayout.CENTER);
        listaSalasFrame.setVisible(true);
    }

    private void abrirEdicaoSala(int id, String nomeAntigo, String tipoAntigo) {
        JFrame edicaoFrame = new JFrame("Editar Sala");
        edicaoFrame.setSize(350, 180);
        edicaoFrame.setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);

        JTextField tfNomeEdicao = new JTextField(nomeAntigo);
        JComboBox<String> cbTipoEdicao = new JComboBox<>(new String[] {
            "Sala de Reunioes", "Sala de Seminarios", "Laboratorio de Graduacao"
        });
        cbTipoEdicao.setSelectedItem(tipoAntigo);

        edicaoFrame.add(new JLabel("Novo nome:"));
        edicaoFrame.add(tfNomeEdicao);

        edicaoFrame.add(new JLabel("Novo tipo:"));
        edicaoFrame.add(cbTipoEdicao);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> {
            try (Connection conn = Banco.conectar();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE Sala SET nome = ?, tipo = ? WHERE id = ?")) {
                stmt.setString(1, tfNomeEdicao.getText());
                stmt.setString(2, cbTipoEdicao.getSelectedItem().toString());
                stmt.setInt(3, id);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(edicaoFrame, "Sala atualizada com sucesso!");
                edicaoFrame.dispose();
                listaSalasFrame.dispose();
                abrirListaDeSalas();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(edicaoFrame, "Erro ao atualizar sala.");
            }
        });

        edicaoFrame.add(btnAtualizar);
        edicaoFrame.setVisible(true);
    }
}