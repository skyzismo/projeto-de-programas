import java.util.Scanner;

public class SomaColecoes {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int soma = 0;

        while (true) {
            int valor = teclado.nextInt();

            if (valor == -1) {
                if (soma == 0) {
                    break; // fim de todas as coleções
                } else {
                    System.out.println(soma);
                    soma = 0; // inicia próxima coleção
                }
            } else {
                soma += valor;
            }
        }

        teclado.close();
    }
}
