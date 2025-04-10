import java.util.Scanner;

public class AproximacaoSeno {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double graus = teclado.nextDouble();
        int k = teclado.nextInt();

        double x = Math.toRadians(graus); // converte para radianos
        double seno = 0.0;
        double termo;
        int expoente = 1;
        double numerador = x;
        double fatorial = 1.0;

        for (int i = 1; i <= k; i++) {
            // Atualiza o termo (x^expoente / expoente!)
            if (i == 1) {
                termo = numerador / fatorial;
            } else {
                numerador *= x * x;       // x^n
                fatorial *= (expoente - 1) * expoente; // n!
                termo = numerador / fatorial;
            }

            // Alterna o sinal: + - + - ...
            if (i % 2 == 0) {
                seno -= termo;
            } else {
                seno += termo;
            }

            System.out.printf("%.10f\n", seno);
            expoente += 2;
        }

        teclado.close();
    }
}
