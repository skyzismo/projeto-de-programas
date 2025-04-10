import java.util.ArrayList;
import java.util.Scanner;

public class AreaPoligono {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        // Lê o vetor x
        while (true) {
            double valor = teclado.nextDouble();
            if (valor == -1) break;
            x.add(valor);
        }

        // Lê o vetor y
        while (true) {
            double valor = teclado.nextDouble();
            if (valor == -1) break;
            y.add(valor);
        }

        // Calcula área usando a fórmula fornecida
        double soma = 0.0;
        int n = x.size();

        for (int i = 0; i < n - 1; i++) {
            soma += (x.get(i + 1) + x.get(i)) * (y.get(i + 1) - y.get(i));
        }

        double area = 0.5 * Math.abs(soma);

        System.out.printf("%.4f\n", area);
        teclado.close();
    }
}
