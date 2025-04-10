import javax.swing.*;
import java.awt.*;

public class RosaPolar extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int a = 100; // amplitude
        int k = 4;   // número de pétalas (se k for par, tem 2k pétalas)

        g2.translate(width / 2, height / 2);
        g2.setColor(Color.PINK);

        for (double theta = 0; theta < 2 * Math.PI * k; theta += 0.01) {
            double r = a * Math.cos(k * theta);
            int x = (int) (r * Math.cos(theta));
            int y = (int) (r * Math.sin(theta));
            g2.fillOval(x, y, 2, 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Curva Rosa 🌹");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new RosaPolar());
        frame.setVisible(true);
    }
}
