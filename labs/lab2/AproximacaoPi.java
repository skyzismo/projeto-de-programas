import java.util.Scanner;

public class AproximacaoPi {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int n = teclado.nextInt();
        double pi = 3.0;
        System.out.printf("%.6f\n", pi); // primeira aproximação sempre é 3

        double termo;
        int x = 2;

        for (int i = 1; i < n; i++) {
            termo = 4.0 / (x * (x + 1) * (x + 2));

            if (i % 2 == 1) {
                pi += termo;
            } else {
                pi -= termo;
            }

            System.out.printf("%.6f\n", pi);
            x += 3; // próximo conjunto de denominadores
        }

        teclado.close();
    }
}
