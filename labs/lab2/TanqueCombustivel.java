import java.util.Scanner;

public class TanqueCombustivel {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double r = teclado.nextDouble();  // raio da esfera
        double x = teclado.nextDouble();  // altura do ar
        int opcao = teclado.nextInt();    // 1 = ar, 2 = combustível

        double pi = Math.PI;

        // Volume da calota esférica (volume do ar)
        double volumeAr = (pi / 3.0) * Math.pow(x, 2) * (3 * r - x);

        if (opcao == 1) {
            System.out.printf("%.4f\n", volumeAr);
        } else if (opcao == 2) {
            // Volume total da esfera
            double volumeTotal = (4.0 / 3.0) * pi * Math.pow(r, 3);
            double volumeCombustivel = volumeTotal - volumeAr;
            System.out.printf("%.4f\n", volumeCombustivel);
        }

        teclado.close();
    }
}
