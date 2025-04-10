import java.util.Scanner;
import java.util.ArrayList;

public class DistanciaAviao {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int[][] tempo = {
            { 0,  2, 11,  6, 15, 11,  1},
            { 2,  0,  7, 12,  4,  2, 15},
            {11,  7,  0, 11,  8,  3, 13},
            { 6, 12, 11,  0, 10,  2,  1},
            {15,  4,  8, 10,  0,  5,  2},
            {11,  2,  3,  2,  5,  0, 14},
            { 1, 15, 13,  1,  2, 14,  0}
        };

        int[] codigos = {111, 222, 333, 444, 555, 666, 777};

        ArrayList<Integer> rotas = new ArrayList<>();

        // Leitura da sequência de cidades
        while (true) {
            int cidade = teclado.nextInt();
            if (cidade == -1) break;

            // Converter código da cidade para índice da matriz
            for (int i = 0; i < codigos.length; i++) {
                if (codigos[i] == cidade) {
                    rotas.add(i);
                    break;
                }
            }
        }

        // Cálculo do tempo total
        int total = 0;
        for (int i = 0; i < rotas.size() - 1; i++) {
            int origem = rotas.get(i);
            int destino = rotas.get(i + 1);
            total += tempo[origem][destino];
        }

        System.out.println(total);
        teclado.close();
    }
}
