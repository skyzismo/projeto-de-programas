import java.util.Scanner;

public class CaixaEletronico {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int valor = teclado.nextInt();

        if (valor <= 0 || valor % 2 != 0) {
            System.out.println("Valor Invalido");
        } else {
            int notas50 = valor / 50;
            valor = valor % 50;

            int notas10 = valor / 10;
            valor = valor % 10;

            int notas2 = valor / 2;

            System.out.printf("%d notas de R$50, %d notas de R$10 e %d notas de R$2\n", notas50, notas10, notas2);
        }

        teclado.close();
    }
}
