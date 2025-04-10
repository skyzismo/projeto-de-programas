import java.util.Scanner;

public class RaizDois {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double frac = 0.0;

        for (int i = 1; i <= n; i++) {
            frac = 1.0 / (2.0 + frac);
            double resultado = 1.0 + frac;
            System.out.printf("%.14f\n", resultado);
        }

        sc.close();
    }
}
