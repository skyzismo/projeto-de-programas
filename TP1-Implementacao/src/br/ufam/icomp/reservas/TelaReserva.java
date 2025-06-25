/**
 * Classe responsável pela interface de reserva de salas.
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
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TelaReserva extends JFrame {
    private JComboBox<String> cbSalas = new JComboBox<>();
    private JComboBox<String> cbUsuarios = new JComboBox<>();
    private JTextField txtData = new JTextField(10);
    private JSpinner spinnerHoraInicio;
    private JSpinner spinnerMinutoInicio;
    private JSpinner spinnerHoraFim;
    private JSpinner spinnerMinutoFim;

    public TelaReserva() {
        setTitle("Reservar Sala");
        setSize(600, 350);
        setLayout(new GridLayout(7, 3));
        setLocationRelativeTo(null);

        carregarSalas();
        carregarUsuarios();

        // Configuração do campo de data com botão de calendário
        JPanel panelData = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtData.setEditable(false);
        panelData.add(txtData);
        JButton btnCalendario = new JButton("Selecionar Data");
        btnCalendario.addActionListener(e -> mostrarCalendario());
        panelData.add(btnCalendario);

        // Configuração dos spinners para hora início e fim
        spinnerHoraInicio = new JSpinner(new SpinnerNumberModel(8, 0, 23, 1));
        spinnerMinutoInicio = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        spinnerHoraFim = new JSpinner(new SpinnerNumberModel(9, 0, 23, 1));
        spinnerMinutoFim = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(e -> reservarSala());

        add(new JLabel("Sala:"));
        add(cbSalas);
        add(new JLabel("Usuario:"));
        add(cbUsuarios);
        add(new JLabel("Data:"));
        add(panelData);
        add(new JLabel("Horario de Inicio:"));
        add(criarPainelHora(spinnerHoraInicio, spinnerMinutoInicio));
        add(new JLabel("Horario de Fim:"));
        add(criarPainelHora(spinnerHoraFim, spinnerMinutoFim));
        add(btnReservar);
    }

    private JPanel criarPainelHora(JSpinner hora, JSpinner minuto) {
        JPanel panelHora = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHora.add(hora);
        panelHora.add(new JLabel(":"));
        panelHora.add(minuto);
        return panelHora;
    }

    private void mostrarCalendario() {
        JDialog dialog = new JDialog(this, "Selecione a Data", true);
        JCalendar calendar = new JCalendar();
        calendar.addPropertyChangeListener(e -> {
            if ("calendar".equals(e.getPropertyName())) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                txtData.setText(sdf.format(calendar.getDate()));
                dialog.dispose();
            }
        });
        dialog.add(calendar);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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
        if (txtData.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione uma data!");
            return;
        }

        try (Connection conn = Banco.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Reserva (sala_id, usuario_id, data, hora_inicio, hora_fim) VALUES (?, ?, ?, ?, ?)")) {

            String salaStr = cbSalas.getSelectedItem().toString();
            String usuarioStr = cbUsuarios.getSelectedItem().toString();

            String horaInicio = String.format("%02d:%02d", 
                (Integer)spinnerHoraInicio.getValue(), 
                (Integer)spinnerMinutoInicio.getValue());
            
            String horaFim = String.format("%02d:%02d", 
                (Integer)spinnerHoraFim.getValue(), 
                (Integer)spinnerMinutoFim.getValue());

            stmt.setInt(1, Integer.parseInt(salaStr.split(":")[0]));
            stmt.setInt(2, Integer.parseInt(usuarioStr.split(":")[0]));
            stmt.setString(3, txtData.getText());
            stmt.setString(4, horaInicio);
            stmt.setString(5, horaFim);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao realizar reserva.");
        }
    }
}

class JCalendar extends JPanel {
    private JSpinner monthSpinner;
    private JSpinner yearSpinner;
    private JPanel daysPanel;
    private Calendar calendar;

    public JCalendar() {
        setLayout(new BorderLayout());
        calendar = Calendar.getInstance();

        // Painel superior com mês e ano
        JPanel topPanel = new JPanel();
        monthSpinner = new JSpinner(new SpinnerNumberModel(
            calendar.get(Calendar.MONTH) + 1, 1, 12, 1));
        yearSpinner = new JSpinner(new SpinnerNumberModel(
            calendar.get(Calendar.YEAR), 1900, 2100, 1));
        
        topPanel.add(new JLabel("Mes:"));
        topPanel.add(monthSpinner);
        topPanel.add(new JLabel("Ano:"));
        topPanel.add(yearSpinner);
        
        add(topPanel, BorderLayout.NORTH);

        // Painel de dias
        daysPanel = new JPanel(new GridLayout(0, 7));
        add(daysPanel, BorderLayout.CENTER);

        // Listeners para atualizar o calendário
        monthSpinner.addChangeListener(e -> updateCalendar());
        yearSpinner.addChangeListener(e -> updateCalendar());

        updateCalendar();
    }

    private void updateCalendar() {
        daysPanel.removeAll();
        
        int month = (Integer) monthSpinner.getValue() - 1;
        int year = (Integer) yearSpinner.getValue();
        
        calendar.set(year, month, 1);
        
        // Cabeçalho com dias da semana
        String[] days = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"};
        for (String day : days) {
            daysPanel.add(new JLabel(day, JLabel.CENTER));
        }
        
        // Dias do mês
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        // Espaços vazios para os dias antes do primeiro dia do mês
        for (int i = 1; i < firstDayOfWeek; i++) {
            daysPanel.add(new JLabel(""));
        }
        
        // Botões para cada dia do mês
        for (int day = 1; day <= daysInMonth; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            final int selectedDay = day;
            dayButton.addActionListener(e -> {
                calendar.set(Calendar.DAY_OF_MONTH, selectedDay);
                firePropertyChange("calendar", false, true);
            });
            daysPanel.add(dayButton);
        }
        
        daysPanel.revalidate();
        daysPanel.repaint();
    }

    public java.util.Date getDate() {
        return calendar.getTime();
    }
}