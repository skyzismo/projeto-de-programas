import java.util.Scanner;

public class AreaTriangulo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double a = teclado.nextDouble();
        double b = teclado.nextDouble();
        double c = teclado.nextDouble();

        // Verifica validade do triângulo
        if (a + b > c && a + c > b && b + c > a) {
            double s = (a + b + c) / 2;
            double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

            // Imprime com 2 casas decimais
            System.out.printf("%.2f\n", area);
        } else {
            System.out.println("Triangulo invalido");
        }

        teclado.close();
    }
}
