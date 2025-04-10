import java.util.ArrayList;
import java.util.Scanner;

public class TimeFutebol {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        ArrayList<Integer> golsTime = new ArrayList<>();
        ArrayList<Integer> golsAdversario = new ArrayList<>();

        // Lê vetor do time
        while (true) {
            int valor = teclado.nextInt();
            if (valor == -1) break;
            golsTime.add(valor);
        }

        // Lê vetor do adversário
        while (true) {
            int valor = teclado.nextInt();
            if (valor == -1) break;
            golsAdversario.add(valor);
        }

        int vitorias = 0, empates = 0, derrotas = 0;

        for (int i = 0; i < golsTime.size(); i++) {
            int time = golsTime.get(i);
            int adversario = golsAdversario.get(i);

            if (time > adversario) {
                vitorias++;
            } else if (time == adversario) {
                empates++;
            } else {
                derrotas++;
            }
        }

        System.out.printf("%d %d %d\n", vitorias, empates, derrotas);
        teclado.close();
    }
}
