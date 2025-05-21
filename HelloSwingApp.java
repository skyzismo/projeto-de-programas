import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelloSwingApp {

    public static void main(String[] args) {
        // Garante que a UI seja criada na Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new HelloSwingApp().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Cria o frame principal
        JFrame frame = new JFrame("Hello Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null); // centraliza na tela

        // Define um layout simples
        frame.setLayout(new BorderLayout(10, 10));

        // Label de instrução
        JLabel promptLabel = new JLabel("Digite o nome de uma pessoa muito especial:");
        promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(promptLabel, BorderLayout.NORTH);

        // Painel central com text field e botão
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JTextField nameField = new JTextField(15);
        JButton greetButton = new JButton("Clique e veja a magia");
        centerPanel.add(nameField);
        centerPanel.add(greetButton);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Label para exibir a saudação
        JLabel greetingLabel = new JLabel(" ");
        greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(greetingLabel, BorderLayout.SOUTH);

        // ActionListener para o botão
        greetButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                greetingLabel.setText("Por favor, digite um nome.");
            } else {
                greetingLabel.setText("Eu te amo, " + name + ", eu te amo!!! <3 <3 <3");
            }
        });

        // Torna o frame visível
        frame.setVisible(true);
    }
}
