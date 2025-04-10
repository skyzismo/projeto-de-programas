import java.util.Scanner;

public class PontoReta {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double x = teclado.nextDouble();
        double y = teclado.nextDouble();

        // Calcula 2x + y
        double resultado = 2 * x + y;

        // Definimos uma margem de erro para comparar doubles
        double epsilon = 0.0001;

        if (Math.abs(resultado - 3.0) < epsilon) {
            System.out.printf("Ponto (%.1f, %.1f) pertence a reta 2x + y = 3.\n", x, y);
        } else {
            System.out.printf("Ponto (%.1f, %.1f) nao pertence a reta 2x + y = 3.\n", x, y);
        }

        teclado.close();
    }
}
