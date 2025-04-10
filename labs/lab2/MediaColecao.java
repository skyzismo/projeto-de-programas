import java.util.Scanner;

public class MediaColecao {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int soma = 0;
        int quantidade = 0;
        int valor;

        do {
            valor = teclado.nextInt();
            if (valor != -1) {
                soma += valor;
                quantidade++;
            }
        } while (valor != -1);

        if (quantidade > 0) {
            double media = (double) soma / quantidade;
            System.out.printf("%.2f\n", media);
        }

        teclado.close();
    }
}
