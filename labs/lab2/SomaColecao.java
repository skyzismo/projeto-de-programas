import java.util.Scanner;

public class SomaColecao {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int soma = 0;
        int numero;

        do {
            numero = teclado.nextInt();
            if (numero != -1) {
                soma += numero;
            }
        } while (numero != -1);

        System.out.println(soma);
        teclado.close();
    }
}
