import java.util.Scanner;

public class Desconto {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double valor = teclado.nextDouble();
        double valorFinal;

        if (valor >= 200.00) {
            valorFinal = valor * 0.95; // aplica 5% de desconto
        } else {
            valorFinal = valor; // sem desconto
        }

        // Imprime com 2 casas decimais
        System.out.printf("%.2f\n", valorFinal);

        teclado.close();
    }
}
