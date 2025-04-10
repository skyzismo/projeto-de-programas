import java.util.Scanner;

public class NumeroNeperiano {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int k = teclado.nextInt();
        double e = 0.0;
        double fatorial = 1.0;

        for (int i = 0; i < k; i++) {
            if (i > 0) {
                fatorial *= i; // calcula i!
            }
            e += 1.0 / fatorial;
        }

        System.out.printf("%.6f\n", e);
        teclado.close();
    }
}
