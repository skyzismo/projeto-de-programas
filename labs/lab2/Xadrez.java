import java.util.Scanner;

public class Xadrez {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int n = teclado.nextInt();  // Lê a quantidade de linhas (e colunas)

        for (int i = 0; i < n; i++) {
            // Se a linha for ímpar, imprime um espaço no início
            if (i % 2 != 0) {
                System.out.print(" ");
            }

            // Imprime a linha com n asteriscos separados por espaço
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }

            // Pula para a próxima linha
            System.out.println();
        }

        teclado.close();
    }
}
