import java.util.Scanner;

public class DiaSemana {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int[][] matriz = new int[1000][7]; // tamanho arbitrário grande
        int n = 0;

        // leitura da matriz até -1
        while (true) {
            boolean fim = false;
            for (int j = 0; j < 7; j++) {
                int valor = teclado.nextInt();
                if (valor == -1) {
                    fim = true;
                    break;
                }
                matriz[n][j] = valor;
            }
            if (fim) break;
            n++;
        }

        int[] somaDias = new int[7];

        // soma as colunas (dias da semana)
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < n; i++) {
                somaDias[j] += matriz[i][j];
            }
        }

        // descobre o maior valor
        int max = somaDias[0];
        for (int j = 1; j < 7; j++) {
            if (somaDias[j] > max) {
                max = somaDias[j];
            }
        }

        // imprime os dias com a soma máxima
        for (int j = 0; j < 7; j++) {
            if (somaDias[j] == max) {
                System.out.println(j + 1); // dias são de 1 a 7
            }
        }

        teclado.close();
    }
}
