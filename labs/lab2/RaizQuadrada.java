import java.util.*;

public class RaizQuadrada {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double entrada = teclado.nextDouble();

        double raiz = Math.sqrt(entrada);

        System.out.printf("%.4f\n", raiz);

        teclado.close();
    }
}
